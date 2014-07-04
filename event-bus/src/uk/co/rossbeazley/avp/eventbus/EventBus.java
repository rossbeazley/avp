package uk.co.rossbeazley.avp.eventbus;

import uk.co.rossbeazley.avp.eventbus.singlethreaded.PayloadFunction;

public interface EventBus {
    void announce(Object event);

    EventSubscription whenEvent(Object event);

    AnnouncementWithPayload sendPayload(Object any_object);

    void registerProducer(Object event, PayloadFunction function);
}
