package uk.co.rossbeazley.redux.eventbus.singlethreaded;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.Function;
import uk.co.rossbeazley.redux.eventbus.executor.CanBuildExecutor;
import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;

import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusAnnouncementUsingExecutor implements Executor {

    private boolean executedByExecutor;

    @Before
    public void reset() {
        executedByExecutor = false;
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
           .thenRun(Function.NOOP);
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
