package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.PayloadFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producers {
    private Map<Object, List<PayloadFunction>> payloadFunctionsForEvent = new HashMap<Object, List<PayloadFunction>>();

    Producers() {

    }

    void notifyProducersOfNewSubscriber(Object event, ExecutorEventSubscription subscription) {
        if (payloadFunctionsForEvent.containsKey(event)) {
            List<PayloadFunction> payloadFunctions = payloadFunctionsForEvent.get(event);
            subscription.registerProducers(payloadFunctions);
        }
    }

    void add(Object event, PayloadFunction function) {
        List<PayloadFunction> payloadFunctions = payloadFunctionsForEvent.get(event);

        if (payloadFunctions == null) {
            payloadFunctions = new ArrayList<PayloadFunction>();
            payloadFunctionsForEvent.put(event, payloadFunctions);
        }

        payloadFunctions.add(function);

    }
}