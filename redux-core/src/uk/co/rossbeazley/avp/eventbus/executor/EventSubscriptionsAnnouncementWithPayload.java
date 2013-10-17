package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.AnnouncementWithPayload;

class EventSubscriptionsAnnouncementWithPayload implements AnnouncementWithPayload {
    private final EventSubscriptions eventSubscriptions;
    private final Object payload;

    EventSubscriptionsAnnouncementWithPayload(EventSubscriptions eventSubscriptions, Object any_object) {
        this.eventSubscriptions = eventSubscriptions;
        payload = any_object;
    }

    @Override
    public void withEvent(Object event) {
        eventSubscriptions.announce(event, payload);
    }
}
