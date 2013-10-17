package uk.co.rossbeazley.avp.eventbus.singlethreaded;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.CanBuildExecutor;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementWithoutPayload implements Function, Executor {

    private boolean notifed;
    private boolean executedByExecutor;

    @Before
    public void reset() {
        notifed = false;
        executedByExecutor = false;
    }

    @Test
    public void aSubscriberToAnEventIsNotifiedOfThatEvent() {
        String AN_EVENT = "event";
        EventBus bus = anEventBusSubscribedToEvent(AN_EVENT);
        bus.announce(AN_EVENT);
        assertThat(notifed, is(true));
    }

    @Test
    public void aSubscriberToAnEventIsNotifiedOnAnExecutorsThread() {
        String AN_EVENT = "event";
        EventBus bus = anEventBusSubscribedToEvent(AN_EVENT);
        bus.announce(AN_EVENT);
        assertThat(executedByExecutor,is(true));
    }

    private EventBus anEventBusSubscribedToEvent(String AN_EVENT) {
        EventBus bus = createExecutorEventBus();
        bus.whenEvent(AN_EVENT)
           .thenRun(this);
        return bus;
    }

    private EventBus createExecutorEventBus() {
        return new ExecutorEventBus(
                new CanBuildExecutor() {
                    @Override
                    public Executor executor() {
                        return EventBusAnnouncementWithoutPayload.this;
                    }
                }
        );
    }

    public void invoke() {
        notifed = true;
    }

    @Override
    public void execute(Runnable runnable) {
        executedByExecutor = true;
        runnable.run();
    }

}
