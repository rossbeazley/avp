package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactoryInjector;
import uk.co.rossbeazley.avp.android.ui.results.InjectableResultsFragment;
import uk.co.rossbeazley.avp.android.ui.results.InjectableResultsFragmentInjector;
import uk.co.rossbeazley.avp.android.ui.search.InjectableSearchFragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragmentInjector;
import uk.co.rossbeazley.avp.android.ui.urloader.InjectableUrlLoaderFragment;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragmentInjector;
import uk.co.rossbeazley.avp.android.ui.videoplayer.InjectableVideoPlayerFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragmentInjector;

public class DependencyInjectionFrameworkFactory {

    public DependenciesService createDependencyInjectionFramework(final ApplicationServices services) {
        DependencyInjectors injectorsByClass = new DependencyInjectors() {{
            register(InjectableVideoPlayerFragment.class, new VideoPlayerFragmentInjector(services.eventbus()));
            register(InjectableSearchFragment.class, new SearchFragmentInjector(services.eventbus()));
            register(InjectableUrlLoaderFragment.class, new UrlLoaderFragmentInjector(services.eventbus()));
            register(InjectableResultsFragment.class, new InjectableResultsFragmentInjector());

            register(NeedsAnInflatedViewFactory.class, new NeedsAnInflatedViewFactoryInjector());
        }};
        return new DependenciesService(injectorsByClass);
    }

}