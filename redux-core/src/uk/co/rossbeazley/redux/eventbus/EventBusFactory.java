package uk.co.rossbeazley.redux.eventbus;

import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 20/09/13
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
public class EventBusFactory {

    public static EventBus createEventBus() {
        return new ExecutorEventBus();
    }

}
