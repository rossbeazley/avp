package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.search.InjectableSearchFragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragmentInjector;
import uk.co.rossbeazley.avp.android.ui.videoplayer.InjectableVideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentInjector;

public class DependencyInjectionFrameworkFactory {

    public DependenciesService createDependencyInjectionFramework(final ApplicationServices services) {
        // time to pull this out into its own object, maybe use this directly in tests when testing an injetor
        DependencyInjectors injectorsByClass = new DependencyInjectors() {{
            register(InjectableVideoPlayerFragment.class, new VideoPlayerFragmentInjector(services.eventbus()));
            register(InjectableSearchFragment.class, new SearchFragmentInjector(services.eventbus()));
        }};
        return new DependenciesService(injectorsByClass);
    }
}