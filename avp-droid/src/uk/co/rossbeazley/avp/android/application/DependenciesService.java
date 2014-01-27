package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class DependenciesService {
    private EventBus eventBus;

    public DependenciesService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    void injectDependencies(Object fragment) {

        if (fragment instanceof VideoPlayerFragment) {
            VideoPlayerFragmentScreenFactory fragmentScreenFactory = new VideoPlayerFragmentScreenFactory(eventBus);
            ((VideoPlayerFragment) fragment).setFragmentScreenFactory(fragmentScreenFactory);
        }
    }
}
