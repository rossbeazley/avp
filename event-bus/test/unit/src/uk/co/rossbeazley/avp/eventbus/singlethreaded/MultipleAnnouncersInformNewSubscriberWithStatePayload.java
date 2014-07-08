package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.PayloadFunction;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultipleAnnouncersInformNewSubscriberWithStatePayload
{
    private ArrayList<Object> announcedPayloads = new ArrayList<Object>(2);

    @Test
    public void twoProducersInformAConsumer()
    {
        EventBus bus = new ExecutorEventBus();

        final Object event = new Object();
        final Object payload = new Object();
        bus.registerProducer(event, new SimplePayloadFunction(payload));
        bus.registerProducer(event, new SimplePayloadFunction(payload));

        bus.whenEvent(event).thenRun(
                new FunctionWithParameter<Object>() {
                    @Override
                    public void invoke(Object payload) {
                        announcedPayloads.add(payload);
                    }
                }
        );

        assertThat(announcedPayloads, length(2));
    }

    private Matcher<ArrayList<Object>> length(final int i) {
        return new BaseMatcher<ArrayList<Object>>() {

            private ArrayList<Object> listToCheck;

            @Override
            public boolean matches(Object o) {
                listToCheck = (ArrayList<Object>) o;
                return listToCheck.size()==i;
            }

            @Override
            public void describeTo(Description description) {
                try
                {
                    description.appendText("Failed lenght check, expecting " + i + " got " + listToCheck.size());
                }
                catch (Exception ignored)
                {
                }
            }
        };
    }


    private static class SimplePayloadFunction implements PayloadFunction {
        private final Object payload;

        public SimplePayloadFunction(Object payload) {
            this.payload = payload;
        }

        @Override
        public void payload(FunctionWithParameter listener)
        {
            listener.invoke(payload);
        }
    }
}
