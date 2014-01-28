package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class DependenciesService {
    private EventBus eventBus;

    private  Map<Class, Injector> injectorsByClass;

    public DependenciesService(EventBus eventBus) {
        this.eventBus = eventBus;
        injectorsByClass = new HashMap<Class, Injector>(1){{
                put(VideoPlayerFragment.class, new VideoPlayerFragmentInjector());
        }};
    }

    void injectDependencies(Object fragment) {
        Injector injector = injectorsByClass.get(fragment.getClass());
        injector.inject(fragment);
    }

    private interface Injector<T> {
        void inject(T fragment );
    }

    private class VideoPlayerFragmentInjector implements Injector<VideoPlayerFragment> {

        @Override
        public void inject(VideoPlayerFragment fragment) {
            VideoPlayerFragmentScreenFactory fragmentScreenFactory = new VideoPlayerFragmentScreenFactory(eventBus);
            fragment.setFragmentScreenFactory(fragmentScreenFactory);
        }
    }
}
