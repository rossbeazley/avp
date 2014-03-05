package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public class SearchNavigationController {

    private final ScreenStack screenStack;

    public SearchNavigationController(ScreenStack screenStack, EventBus bus) {
        this.screenStack = screenStack;
        //bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(Events.APP_START)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        showSearchScreen();
                    }
                });
    }

    private void showSearchScreen() {
        screenStack.pushScreen(SearchScreen.class);
    }

}
