package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class DependenciesService {
    private final DependencyInjectors injectors;

    public DependenciesService(DependencyInjectors injectors) {
        this.injectors = injectors;
    }

    void injectDependencies(Object fragment) {
        DependencyInjectors.Injector injector = injectors.injector(fragment.getClass());
        injector.inject(fragment);
    }

}
