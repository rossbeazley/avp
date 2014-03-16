package uk.co.rossbeazley.avp.android.ui.urloader;

import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 31/01/2014
* Time: 15:37
* To change this template use File | Settings | File Templates.
*/
public class UrlLoaderFragmentInjector implements DependenciesService.Injector<InjectableUrlLoaderFragment> {
    private EventBus bus;

    public UrlLoaderFragmentInjector(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void inject(InjectableUrlLoaderFragment homeFragment) {
        homeFragment.injectFragmentScreenFactory(new UrlLoaderFragmentScreenFactory(bus));
    }
}
