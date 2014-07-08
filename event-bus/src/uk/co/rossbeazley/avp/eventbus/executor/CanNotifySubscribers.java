package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

interface CanNotifySubscribers
{
    void eventFunctionRegistered(Object event, FunctionWithParameter function);

    static final CanNotifySubscribers NULL = new CanNotifySubscribers() {public void eventFunctionRegistered(Object event, FunctionWithParameter function) { } };
}
