package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;


public class ResultsNavigationController {

    public ResultsNavigationController(final ScreenStack screenStack, final EventBus bus) {
        bus.whenEvent(Events.SEARCH_CREATED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        screenStack.pushScreen(ResultsScreen.class); //KEVIN - my ui navigation has just broken,
                        // how do i get the payload into the presenter and thus the screen?
                        // for now I can show nothing and wait for the results
                        // maybe my presenters are singletons?
                    }
                });
    }
}
