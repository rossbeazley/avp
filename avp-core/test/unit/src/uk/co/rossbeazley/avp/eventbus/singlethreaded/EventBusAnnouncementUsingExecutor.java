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

public class EventBusAnnouncementUsingExecutor implements Executor {

    public static final Function NO_OP = new Function() {
        public void invoke() { }
    };

    private boolean executedByExecutor;

    @Before
    public void reset() {
        executedByExecutor = false;
    }

    @Test
    public void aSubscriberToAnEventIsNotifiedOnAnExecutorsThread() {
        String ANY_EVENT = "event";
        EventBus bus = anEventBusSubscribedToEvent(ANY_EVENT);
        bus.announce(ANY_EVENT);
        assertThat(executedByExecutor,is(true));
    }

    private EventBus anEventBusSubscribedToEvent(String AN_EVENT) {
        EventBus bus = createExecutorEventBus();
        bus.whenEvent(AN_EVENT)
           .thenRun(NO_OP);
        return bus;
    }

    private EventBus createExecutorEventBus() {
        return new ExecutorEventBus(
                new CanBuildExecutor() {
                    @Override
                    public Executor executor() {
                        return EventBusAnnouncementUsingExecutor.this;
                    }
                }
        );
    }

    @Override
    public void execute(Runnable runnable) {
        executedByExecutor = true;
        runnable.run();
    }

}
