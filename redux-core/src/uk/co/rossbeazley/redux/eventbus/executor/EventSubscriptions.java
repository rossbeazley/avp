package uk.co.rossbeazley.redux.eventbus.executor;

import uk.co.rossbeazley.redux.eventbus.EventSubscription;

import java.util.HashMap;
import java.util.Map;

class EventSubscriptions {

    private Map<Object, Functions> subscriptionsPerEvent = new HashMap<Object, Functions>();


    EventSubscription addSubscriberForEvent(Object event, ExecutorEventSubscription subscription) {
        Functions functions;
        if (subscriptionsPerEvent.containsKey(event)) {
            functions = subscriptionsPerEvent.get(event);
        } else {
            functions = new Functions();
            subscriptionsPerEvent.put(event, functions);
        }
        functions.addSubscriber(subscription);
        return subscription;
    }

    void announce(Object event) {
        Functions functions = subscriptionsPerEvent.get(event);
        if (functions != null) {
            functions.invoke();
        }
    }

    void announce(Object event, Object payload) {
        Functions functions = subscriptionsPerEvent.get(event);
        if (functions != null) {
            functions.invoke(payload);
        }
    }
}
