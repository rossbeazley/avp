package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.EventSubscription;

import java.util.HashMap;
import java.util.Map;

class EventSubscriptions {

    private Map<Object, Functions> subscriptionsPerEvent = new HashMap<Object, Functions>();


    EventSubscription addSubscriberForEvent(Object event, ExecutorEventSubscription subscription) {
        Functions functions;
        if (subscriptionsPerEvent.containsKey(event)) {
            functions = functions(event);
        } else {
            functions = new Functions();
            subscriptionsPerEvent.put(event, functions);
        }
        functions.addSubscriber(subscription);
        return subscription;
    }

    void announce(Object event) {
        functions(event).invoke();
    }

    void announce(Object event, Object payload) {
        functions(event).invoke(payload);
    }

    private Functions functions(Object event) {
        return subscriptionsPerEvent.containsKey(event) ? subscriptionsPerEvent.get(event) : Functions.EMPTY;
    }
}
