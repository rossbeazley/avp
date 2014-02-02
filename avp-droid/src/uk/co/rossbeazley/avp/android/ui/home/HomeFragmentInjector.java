package uk.co.rossbeazley.avp.android.ui.home;

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
public class HomeFragmentInjector implements DependencyInjectors.Injector<InjectableHomeFragment> {
    private EventBus bus;

    public HomeFragmentInjector(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void inject(InjectableHomeFragment homeFragment) {
        homeFragment.setInflatedViewFactory(new DefaultInflatedViewFactory());
        homeFragment.setFragmentScreenFactory(new HomeFragmentScreenFactory(bus));
    }
}
