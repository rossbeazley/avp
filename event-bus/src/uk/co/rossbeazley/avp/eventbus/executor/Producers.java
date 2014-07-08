package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.PayloadFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producers implements CanNotifySubscribers {
    private Map<Object, List<PayloadFunction>> payloadFunctionsForEvent = new HashMap<Object, List<PayloadFunction>>();

    void newSubscriber(Object event, ExecutorEventSubscription subscription) {
        subscription.registerProducers(event, payloadFunctionsForEvent.containsKey(event) ? this : CanNotifySubscribers.NULL);
    }

    void add(Object event, PayloadFunction function) {
        List<PayloadFunction> payloadFunctions = payloadFunctionsForEvent.get(event);

        if (payloadFunctions == null) {
            payloadFunctions = new ArrayList<PayloadFunction>();
            payloadFunctionsForEvent.put(event, payloadFunctions);
        }

        payloadFunctions.add(function);

    }

    public void eventFunctionRegistered(Object event, FunctionWithParameter function) {
        for (PayloadFunction payloadFunction : payloadFunctionsForEvent.get(event)) {
            payloadFunction.payload(function);
        }
    }

}