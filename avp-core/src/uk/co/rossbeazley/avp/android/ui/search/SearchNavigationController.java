package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public final class SearchNavigationController {

    private final ScreenStack screenStack;

    public SearchNavigationController(ScreenStack screenStack, EventBus bus) {
        this.screenStack = screenStack;
        bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(UrlLoaderScreenPresenter.USER_WANTS_TO_GOTO_SEARCH)
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
