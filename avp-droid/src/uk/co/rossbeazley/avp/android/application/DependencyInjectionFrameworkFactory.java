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
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class DependencyInjectionFrameworkFactory {

    public DependenciesService createDependencyInjectionFramework(final ApplicationServices services) {
        DependanciesInjectorRegistry injectorRegistry = createDependanciesInjectorRegistry(services.eventbus());
        return new DependenciesService(injectorRegistry);
    }

    public DependanciesInjectorRegistry createDependanciesInjectorRegistry(final EventBus eventbus) {
        return new DependanciesInjectorRegistry() {{
            register(InjectableVideoPlayerFragment.class, new VideoPlayerFragmentInjector(eventbus));
            register(InjectableSearchFragment.class, new SearchFragmentInjector(eventbus));
            register(InjectableUrlLoaderFragment.class, new UrlLoaderFragmentInjector(eventbus));
            register(InjectableResultsFragment.class, new InjectableResultsFragmentInjector());
            register(NeedsAnInflatedViewFactory.class, new NeedsAnInflatedViewFactoryInjector());
        }};
    }

}
