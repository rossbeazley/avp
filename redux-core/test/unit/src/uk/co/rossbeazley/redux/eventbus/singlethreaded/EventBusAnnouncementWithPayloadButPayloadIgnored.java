package uk.co.rossbeazley.redux;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.Function;
import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementWithPayloadButPayloadIgnored implements Function {
    public boolean notifed;

    @Before
    public void reset() {
        notifed = false;
    }

    @Test
    public void aSubscriberToAnEventIsNotifiedOfThatEvent() {

        String AN_EVENT = "event";
        EventBus bus = anEventBusSubscribedToPayloadEvent(AN_EVENT);

        Object ANY_OBJECT = new Object(){ public String toString() { return "SOME PAYLOAD";}};

        bus.sendPayload(ANY_OBJECT).withEvent(AN_EVENT);

        assertThat(notifed,is(true));
    }

    private EventBus anEventBusSubscribedToPayloadEvent(String AN_EVENT) {
        EventBus bus = new ExecutorEventBus();
        bus.whenEvent(AN_EVENT)
           .thenRun(this);
        return bus;
    }

    @Override
    public void invoke() {
        notifed = true;
    }
}
