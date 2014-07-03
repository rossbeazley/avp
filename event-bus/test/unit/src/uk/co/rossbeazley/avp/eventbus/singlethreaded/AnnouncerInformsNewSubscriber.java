package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnnouncerInformsNewSubscriber
{

    private boolean eventNotified;

    @Test
    public void announcerRegisteredSubscriberListens()
    {
        EventBus bus = new ExecutorEventBus();

        Object event = new Object();
        Function function = null;
        bus.registerProducer(event, function);

        bus.whenEvent(event).thenRun(
                new Function() {
                    @Override
                    public void invoke() {
                        eventNotified = true;
                    }
                }
        );

        assertThat(eventNotified, is(true));

    }
}
