package uk.co.rossbeazley.redux.eventbus.executor;

import uk.co.rossbeazley.redux.eventbus.AnnouncementWithPayload;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.EventSubscription;

import java.util.concurrent.Executor;

public class ExecutorEventBus implements EventBus {

    private final CanBuildExecutor canBuildExecutor;
    private EventSubscriptions eventSubscriptions = new EventSubscriptions();

    public ExecutorEventBus() {
        canBuildExecutor = CanBuildExecutor.DEFAULT;
    }

    public ExecutorEventBus(CanBuildExecutor canBuildExecutor) {
        this.canBuildExecutor = canBuildExecutor;
    }


    @Override
    public void announce(Object event) {
        eventSubscriptions.announce(event);
    }

    @Override
    public AnnouncementWithPayload sendPayload(Object any_object) {
        return new EventSubscriptionsAnnouncementWithPayload(eventSubscriptions, any_object);  //To change body of created methods use File | Settings | File Templates.
    }


    @Override
    public EventSubscription whenEvent(Object event) {
        Executor executor = canBuildExecutor.executor();
        return eventSubscriptions.addSubscriberForEvent(event, new ExecutorEventSubscription(executor));
    }


}
