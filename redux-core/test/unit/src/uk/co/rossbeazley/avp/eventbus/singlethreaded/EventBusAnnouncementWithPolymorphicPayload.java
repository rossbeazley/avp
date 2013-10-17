package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementWithPolymorphicPayload {
    public String sausages = "No sausages";
    public String eggs = "No eggs";

    @Test
    public void aSubscriberToAnEventIsNotifiedOfThatEvent() {

        EventBus bus = new ExecutorEventBus();

        String AN_EVENT = "event";

        bus.whenEvent(AN_EVENT)
           .thenRun(new FunctionWithParameter<Sausages>() {
               @Override
               public void invoke(Sausages object) {
                   sausages = object.eatSausage();
               }
           });

        bus.whenEvent(AN_EVENT)
           .thenRun(new FunctionWithParameter<Eggs>() {
               @Override
               public void invoke(Eggs object) {
                   eggs = object.eatEggs();
               }
           });

        Breakfast breakfast = new Breakfast();

        bus.sendPayload(breakfast).withEvent(AN_EVENT);

        assertThat(sausages,is("Yum"));
        assertThat(eggs,is("Yuk, sunny side up"));
    }

    private interface Sausages {
        public String eatSausage();
    }

    private interface Eggs {
        public String eatEggs();
    }

    private class Breakfast implements Sausages, Eggs {

        @Override
        public String eatSausage() {
             return "Yum";
        }

        @Override
        public String eatEggs() {
            return "Yuk, sunny side up";
        }
    }
}
