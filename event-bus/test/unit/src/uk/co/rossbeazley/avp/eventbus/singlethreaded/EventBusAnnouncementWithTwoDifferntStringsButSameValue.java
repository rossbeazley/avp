package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementWithTwoDifferntStringsButSameValue implements FunctionWithParameter<String> {
    public boolean notifed;
    private EventBus bus;

    @Before
    public void reset() {
        notifed = false;
    }

    @Test @Ignore("Come back and fix this in event bus later")
    public void aSubscriberToAnEventIsNotifiedOfThatEvent() {

        String AN_EVENT = new String("event");
        String ANOTHER_EVENT = new String("event");
        EventBus bus = anEventBusSubscribedToPayloadEvent(AN_EVENT);

        Object ANY_OBJECT = "SOME PAYLOAD";

        bus.sendPayload(ANY_OBJECT).withEvent(ANOTHER_EVENT);

        assertThat(notifed,is(false));
    }

    private EventBus anEventBusSubscribedToPayloadEvent(String AN_EVENT) {
        bus = new ExecutorEventBus();
        bus.whenEvent(AN_EVENT)
           .thenRun(this);
        return bus;
    }

    @Override
    public void invoke(String object) {
        notifed = true;
    }
}
