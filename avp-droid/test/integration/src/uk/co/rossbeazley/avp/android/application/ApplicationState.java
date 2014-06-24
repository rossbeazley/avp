package uk.co.rossbeazley.avp.android.application;

import android.os.Bundle;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.screenStack.EmptyFragmentBackStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

class ApplicationState {

    public static final String RUNNING = "RUNNING";
    private static final String STOPPED = "STOPPED";

    private String state;

    public ApplicationState(Bundle outState, EventBus eventBus) {
        rehydrateStateFromBundle(outState, eventBus);

        eventBus.whenEvent(EmptyFragmentBackStack.UI_CLOSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        state = STOPPED;
                    }
                });
    }

    private void rehydrateStateFromBundle(Bundle outState, EventBus eventBus) {
        if(applicationRestored(outState)) {
             eventBus.announce(Events.APP_RESUMED);
         } else {
             eventBus.announce(Events.APP_START);
         }

        state = RUNNING;
    }

    private boolean applicationRestored(Bundle outState) {
        return outState.containsKey(RUNNING);
    }

    public void stateIntoBundle(Bundle outState) {
        outState.putBoolean(state, true);
    }
}
