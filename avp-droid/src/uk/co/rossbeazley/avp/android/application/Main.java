package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentManagerFragmentStack;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerController;

public class Main extends Activity {

    private AVPApplication AVPApplication;
    private DependenciesService dependenciesService;
    private ApplicationServices services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAppServices();
        createControllers(getFragmentManager());
        createApp(services);
        services.intentParser().onIntent(getIntent());
    }

    private void createControllers(FragmentManager fragmentManager) {
        FragmentStack fragmentStack = new FragmentManagerFragmentStack(fragmentManager);
        new VideoPlayerController(fragmentStack,  services.eventbus());
    }

    private void createApp(final ApplicationServices services) {
        AVPApplication = new AVPApplication(services);
    }

    private void createAppServices() {
        services = new ProductionApplicationServices(this.getApplication());
        dependenciesService = new DependenciesService(services.eventbus());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        services.intentParser().onIntent(getIntent());
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
