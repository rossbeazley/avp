package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.screenStack.EmptyFragmentBackStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class ApplicationExitTest implements CanFinishTheApp {

    private String state = "RUNNING";
    private String finished = "finished";

    @Test
    public void applicationFinishesWhenUIClosed()
    {
        EventBus eventBus = new ExecutorEventBus();
        new ApplicationExit(eventBus, this);

        eventBus.announce(EmptyFragmentBackStack.UI_CLOSED);

        assertThat(state, is(finished));
    }

    @Override
    public void finish() {
        state = finished;
    }
}
