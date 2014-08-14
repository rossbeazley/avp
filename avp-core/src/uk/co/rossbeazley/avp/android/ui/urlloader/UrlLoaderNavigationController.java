package uk.co.rossbeazley.avp.android.ui.urlloader;

import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public final class UrlLoaderNavigationController {

    private final ScreenStack screenStack;

    public UrlLoaderNavigationController(ScreenStack screenStack, EventBus bus) {
        this.screenStack = screenStack;
        bindEventListeners(bus);
    }

    private void bindEventListeners(EventBus bus) {
        bus.whenEvent(ApplicationCore.APP_START)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        showSearchScreen();
                    }
                });
    }

    private void showSearchScreen() {
        screenStack.pushScreen(UrlLoaderScreen.class);
    }

}
