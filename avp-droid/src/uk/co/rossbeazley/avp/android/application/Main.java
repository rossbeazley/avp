package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentFromScreen;
import uk.co.rossbeazley.avp.android.ui.ScreenFragmentStack;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.search.SearchNavigationController;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerNavigationController;

public class Main extends Activity {

    private final DependencyInjectionFrameworkFactory dependencyInjectionFrameworkFactory = new DependencyInjectionFrameworkFactory();
    private DependenciesService dependenciesService;
    private ApplicationServices services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        services = createAppServices();
        dependenciesService = dependencyInjectionFrameworkFactory.createDependencyInjectionFramework(services);
        createNavigationViewControllers(getFragmentManager());
        createCoreApp(services);

        parseIntent(getIntent());
    }

    private void parseIntent(Intent intent) {
        services.intentParser().onIntent(intent);
    }

    private void createNavigationViewControllers(FragmentManager fragmentManager) {
        // these should be pulled out into its own object, the whole method
        FragmentFromScreen fragmentFromScreen = null;  //TODO implement fragmentFromScreen, should it be an interface? dunno
        ScreenStack screenStack = new ScreenFragmentStack(fragmentManager, fragmentFromScreen);
        new VideoPlayerNavigationController(screenStack,  services.eventbus());
        new SearchNavigationController(screenStack, services.eventbus());
    }

    private void createCoreApp(final ApplicationServices services) {
        new AVPApplication(services);
    }

    private ApplicationServices createAppServices() {
        return new ProductionApplicationServices(this.getApplication());
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
