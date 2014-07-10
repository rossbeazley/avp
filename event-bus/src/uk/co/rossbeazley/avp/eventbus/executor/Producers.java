package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.PayloadFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producers {
    private final EventFunctionRegisteredObserver eventFunctionRegisteredObserver = new EventFunctionRegisteredObserver();
    private Map<Object, List<PayloadFunction>> payloadFunctionsForEvent = new HashMap<Object, List<PayloadFunction>>();

    void newSubscriberFunction(Object event, ExecutorEventSubscription subscription) {
        subscription.registerProducers(event, payloadFunctionsForEvent.containsKey(event) ? eventFunctionRegisteredObserver : CanNotifySubscribers.NULL);
    }

    void add(Object event, PayloadFunction function) {
        List<PayloadFunction> payloadFunctions = getPayloadFunctionsCreateIfNotExists(event);
        payloadFunctions.add(function);
    }


    private List<PayloadFunction> getPayloadFunctionsCreateIfNotExists(Object event) {
        List<PayloadFunction> payloadFunctions = payloadFunctionsForEvent.get(event);

        if (payloadFunctions == null)
        {
            payloadFunctions = new ArrayList<PayloadFunction>();
            payloadFunctionsForEvent.put(event, payloadFunctions);
        }
        return payloadFunctions;
    }


    private class EventFunctionRegisteredObserver implements CanNotifySubscribers
    {
        public void eventFunctionRegistered(Object event, FunctionWithParameter function)
        {
            for (PayloadFunction payloadFunction : payloadFunctionsForEvent.get(event))
            {
                payloadFunction.payload(function);
            }
        }
    }

    interface CanNotifySubscribers
    {
        void eventFunctionRegistered(Object event, FunctionWithParameter function);

        static final CanNotifySubscribers NULL = new CanNotifySubscribers() {public void eventFunctionRegistered(Object event, FunctionWithParameter function) { } };
    }
}