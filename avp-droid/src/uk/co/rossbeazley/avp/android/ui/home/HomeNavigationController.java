package uk.co.rossbeazley.avp.android.ui.home;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public class HomeNavigationController {

    private final FragmentStack fragmentStack;

    public HomeNavigationController(FragmentStack fragmentStack, EventBus bus) {
        this.fragmentStack = fragmentStack;
        bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(Events.APP_HIDDEN)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        showHomeScreen();
                    }
                });
    }

    private void showHomeScreen() {
        fragmentStack.pushFragment(HomeFragment.class);
    }

}
