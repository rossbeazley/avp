package uk.co.rossbeazley.avp.eventbus;

public interface EventBus {
    void announce(Object event);

    EventSubscription whenEvent(Object event);

    AnnouncementWithPayload sendPayload(Object any_object);

    void registerProducer(Object event, Function function);
}
