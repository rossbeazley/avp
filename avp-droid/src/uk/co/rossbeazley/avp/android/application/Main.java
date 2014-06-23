package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.ui.screenStack.UiNavigationStackFactory;
import uk.co.rossbeazley.avp.eventbus.Function;

import java.util.concurrent.ScheduledExecutorService;

public class Main extends Activity {

    private final UiNavigationStackFactory uiNavigationStackFactory = new UiNavigationStackFactory();
    private final ApplicationServices services = new ProductionApplicationServices(this.getApplication());
    private final ApplicationCore applicationCore = createCoreAppBlocking(services);
    private final DependenciesService dependenciesService = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services, applicationCore);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiNavigationStackFactory.createNavigationViewControllers(getFragmentManager(), services.eventbus());
        parseIntent(getIntent());
        finishWhenFragmentBackstackIsEmpty();

    }

    private void finishWhenFragmentBackstackIsEmpty() {
        services.eventbus().whenEvent(Events.UI_CLOSED).thenRun(new Function() {
            @Override
            public void invoke() {
                Main.this.finish();
            }
        });
    }

    private void parseIntent(Intent intent) {
        services.intentParser().onIntent(intent);
    }

    private ApplicationCore createCoreAppBlocking(final ApplicationServices services) {
        final AVPApplication[] avpApp = new AVPApplication[1];

        services.executeBlockingRunnableNotOnMainThread(new Runnable() {
            @Override
            public void run() {
                ScheduledExecutorService executorService = services.getExecutorService();
                new EventBusLog(services.getLogger(), services.eventbus());
                avpApp[0] = new AVPApplication(services.eventbus(), services.getLogger(), services.getAndroidMediaPlayerFactory(), new ThreadPoolFixedRateExecutor(executorService), services.getMediaRepository());
            }
        });


        return avpApp[0].core;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntent(getIntent());
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        dependenciesService.injectDependencies(fragment);
    }

    @Override
    protected void onPause() {
        super.onPause();
        services.eventbus().announce(Events.APP_HIDDEN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        services.eventbus().announce(Events.APP_SHUTDOWN);
    }


}
