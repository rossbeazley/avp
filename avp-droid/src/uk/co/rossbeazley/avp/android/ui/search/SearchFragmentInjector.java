package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 31/01/2014
* Time: 15:37
* To change this template use File | Settings | File Templates.
*/
public final class SearchFragmentInjector implements DependenciesService.Injector <InjectableSearchFragment> {
    private EventBus bus;

    public SearchFragmentInjector(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void inject(InjectableSearchFragment homeFragment) {
        homeFragment.injectFragmentScreenFactory(new SearchFragmentScreenFactory(bus));
    }
}
