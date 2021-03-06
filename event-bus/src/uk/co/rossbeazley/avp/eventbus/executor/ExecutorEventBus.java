package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.AnnouncementWithPayload;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.EventSubscription;
import uk.co.rossbeazley.avp.eventbus.PayloadFunction;

import java.util.concurrent.Executor;

public class ExecutorEventBus implements EventBus {

    private final CanBuildExecutor canBuildExecutor;
    private final Producers producers = new Producers();
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
        return new EventSubscriptionsAnnouncementWithPayload(eventSubscriptions, any_object);
    }

    @Override
    public void registerProducer(Object event, PayloadFunction function) {
        producers.add(event, function);
    }


    @Override
    public EventSubscription whenEvent(Object event) {
        Executor executor = canBuildExecutor.executor();
        ExecutorEventSubscription subscription = new ExecutorEventSubscription(executor);
        producers.newSubscriberFunction(event, subscription);
        return eventSubscriptions.addSubscriberForEvent(event, subscription);
    }

}
