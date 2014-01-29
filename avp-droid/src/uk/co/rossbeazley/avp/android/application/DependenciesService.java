package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class DependenciesService {
    private EventBus eventBus;

    private  Map<Class, Injector> injectorsByClass;

    public DependenciesService(EventBus eventBus, Map<Class, Injector> injectorsByClass) {
        this.eventBus = eventBus;
        this.injectorsByClass = injectorsByClass;
    }

    void injectDependencies(Object fragment) {
        Injector injector = injectorsByClass.get(fragment.getClass());
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
