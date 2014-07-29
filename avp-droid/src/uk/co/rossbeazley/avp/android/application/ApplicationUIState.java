package uk.co.rossbeazley.avp.android.application;

import android.os.Bundle;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.ui.screenStack.EmptyFragmentBackStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

class ApplicationUIState {

    public static final String RUNNING = "RUNNING";
    private static final String STOPPED = "STOPPED";

    private String state;

    public ApplicationUIState(Bundle outState, EventBus eventBus) {
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
        if(applicationWasRunning(outState)) {
             eventBus.announce(ApplicationCore.APP_RESUMED);
         } else {
             eventBus.announce(ApplicationCore.APP_START);
         }

        state = RUNNING;
    }

    private boolean applicationWasRunning(Bundle outState) {
        return outState != null && outState.containsKey(RUNNING);
    }

    public void stateIntoBundle(Bundle outState) {
        outState.putBoolean(state, true);
    }
}
