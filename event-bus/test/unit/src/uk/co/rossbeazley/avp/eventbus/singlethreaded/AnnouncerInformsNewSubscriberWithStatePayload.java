package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnnouncerInformsNewSubscriberWithStatePayload
{

    private Object announcedPayload;

    @Test
    public void announcerRegisteredSubscriberListens()
    {
        EventBus bus = new ExecutorEventBus();

        final Object event = new Object();
        final Object payload = new Object();
        PayloadFunction function = new PayloadFunction()
        {
            @Override
            public void payload(FunctionWithParameter listener)
            {
                listener.invoke(payload);
            }
        };

        bus.registerProducer(event, function);

        bus.whenEvent(event).thenRun(
                new FunctionWithParameter<Object>() {
                    @Override
                    public void invoke(Object payload) {
                        announcedPayload = payload;
                    }
                }
        );

        assertThat(announcedPayload, is(payload));
    }
}
