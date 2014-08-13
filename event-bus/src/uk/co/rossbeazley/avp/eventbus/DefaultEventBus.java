package uk.co.rossbeazley.avp.eventbus;

import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

public class DefaultEventBus implements EventBus {

    EventBus bus = new ExecutorEventBus();

    @Override
    public void announce(Object event) {
        bus.announce(event);
    }

    @Override
    public EventSubscription whenEvent(Object event) {
        return bus.whenEvent(event);
    }

    @Override
    public AnnouncementWithPayload sendPayload(Object any_object) {
        return bus.sendPayload(any_object);
    }

    @Override
    public void registerProducer(Object event, PayloadFunction function) {
        bus.registerProducer(event,function);
    }
}
