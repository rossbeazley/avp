package uk.co.rossbeazley.redux.eventbus;

public interface EventBus {
    void announce(Object event);

    EventSubscription whenEvent(Object event);

    AnnouncementWithPayload sendPayload(Object any_object);
}
