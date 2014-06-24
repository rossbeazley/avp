package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.log.EventBusLog;
import uk.co.rossbeazley.avp.android.ui.screenStack.UiNavigationStackFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.concurrent.ScheduledExecutorService;

public class Main extends Activity implements CanFinishTheApp{

    private final UiNavigationStackFactory uiNavigationStackFactory = new UiNavigationStackFactory();
    private final ApplicationServices services = new ProductionApplicationServices(this.getApplication());
    private final EventBus eventbus = services.eventbus();
    private final ApplicationCore applicationCore = createCoreAppBlocking(services);
    private final DependenciesService dependenciesService = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services, applicationCore);
    private IntentToEventDispatcher intentParser = services.intentParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        uiNavigationStackFactory.createNavigationViewControllers(getFragmentManager(), eventbus);
        intentParser.onIntent(getIntent(), savedInstanceState);
        new ApplicationExit(eventbus, this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
        intentParser.onIntent(getIntent());
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        dependenciesService.injectDependencies(fragment);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventbus.announce(Events.APP_HIDDEN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventbus.announce(Events.APP_SHUTDOWN);
    }


}
