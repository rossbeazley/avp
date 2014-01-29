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
        Injector injector = injectors.injector(fragment.getClass());
        injector.inject(fragment);
    }

    public interface Injector<T> {
        void inject(T object);
    }

    public static class VideoPlayerFragmentInjector implements Injector<VideoPlayerFragment> {

        private EventBus eventBus;

        public VideoPlayerFragmentInjector(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override
        public void inject(VideoPlayerFragment fragment) {
            VideoPlayerFragmentScreenFactory fragmentScreenFactory = new VideoPlayerFragmentScreenFactory(eventBus);
            fragment.setFragmentScreenFactory(fragmentScreenFactory);
        }
    }
}
