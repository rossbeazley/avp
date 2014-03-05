package uk.co.rossbeazley.avp.android.ui.urloader;

import uk.co.rossbeazley.avp.android.application.DependencyInjectors;
import uk.co.rossbeazley.avp.android.ui.DefaultInflatedViewFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 31/01/2014
* Time: 15:37
* To change this template use File | Settings | File Templates.
*/
public class UrlLoaderFragmentInjector implements DependencyInjectors.Injector<InjectableUrlLoaderFragment> {
    private EventBus bus;

    public UrlLoaderFragmentInjector(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void inject(InjectableUrlLoaderFragment homeFragment) {
        homeFragment.injectInflatedViewFactory(new DefaultInflatedViewFactory());
        homeFragment.injectFragmentScreenFactory(new UrlLoaderFragmentScreenFactory(bus));
    }
}
