package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentManagerFragmentStack;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerNavigationController;

import java.util.HashMap;
import java.util.Map;

public class Main extends Activity {

    private AVPApplication AVPApplication;
    private DependenciesService dependenciesService;
    private ApplicationServices services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAppServices();
        createNavigationViewControllers(getFragmentManager());
        createCoreApp(services);

        parseIntent(getIntent());
    }

    private void parseIntent(Intent intent) {
        services.intentParser().onIntent(intent);
    }

    private void createNavigationViewControllers(FragmentManager fragmentManager) {
        FragmentStack fragmentStack = new FragmentManagerFragmentStack(fragmentManager);
        new VideoPlayerNavigationController(fragmentStack,  services.eventbus());
    }

    private void createCoreApp(final ApplicationServices services) {
        AVPApplication = new AVPApplication(services);
    }

    private void createAppServices() {
        services = new ProductionApplicationServices(this.getApplication());
        Map<Class, DependenciesService.Injector> injectorsByClass = new HashMap<Class, DependenciesService.Injector>(1) {{
            put(VideoPlayerFragment.class, new DependenciesService.VideoPlayerFragmentInjector(services.eventbus()));
        }};
        dependenciesService = new DependenciesService(services.eventbus(), injectorsByClass);
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
