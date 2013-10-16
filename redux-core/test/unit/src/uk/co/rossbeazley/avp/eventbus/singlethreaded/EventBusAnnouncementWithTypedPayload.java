package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementWithTypedPayload implements FunctionWithParameter<String> {
    public boolean notifed;
    public String payload;

    @Before
    public void reset() {
        notifed = false;
        payload = "NO PAYLOAD";
    }

    @Test
    public void aSubscriberToAnEventIsNotifiedOfThatEvent() {

        String AN_EVENT = "event";
        EventBus bus = anEventBusSubscribedToPayloadEvent(AN_EVENT);

        Object ANY_OBJECT = "SOME PAYLOAD";

        bus.sendPayload(ANY_OBJECT).withEvent(AN_EVENT);

        assertThat(notifed,is(true));
        assertThat(payload,is(ANY_OBJECT));
    }

    private EventBus anEventBusSubscribedToPayloadEvent(String AN_EVENT) {
        EventBus bus = new ExecutorEventBus();
        bus.whenEvent(AN_EVENT)
           .thenRun(this);
        return bus;
    }

    @Override
    public void invoke(String object) {
        notifed = true;
        payload = object;

    }
}
