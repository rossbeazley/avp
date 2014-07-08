package uk.co.rossbeazley.avp.eventbus.executor;

import uk.co.rossbeazley.avp.eventbus.EventSubscription;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

import java.util.concurrent.Executor;

class ExecutorEventSubscription implements EventSubscription, AnnouncementFunction {

    private final Executor executor;
    private FunctionWithParameter functionWithParameter;

    private static final Object NO_PAYLOAD = null;
    private Object event;
    private CanNotifySubscribers producers;

    public ExecutorEventSubscription(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void thenRun(final Function function) {
        this.functionWithParameter = new FunctionWithParameter() {
            @Override
            public void invoke(Object object) {
                function.invoke();
            }
        };
    }

    @Override
    public void thenRun(FunctionWithParameter function) {
        this.functionWithParameter = function;
        producers.eventFunctionRegistered(event, function);
    }


    @Override
    public void invoke() {
        invoke(NO_PAYLOAD);
    }

    @Override
    public void invoke(final Object payload) {
        executorInvoke(functionWithParameter, payload);
    }

    @SuppressWarnings("unchecked")
    private void executorInvoke(final FunctionWithParameter function, final Object payload) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    function.invoke(payload);
                } catch (ClassCastException e) {
                    //exception squashed
                }
            }
        });
    }

    public void registerProducers(Object event, CanNotifySubscribers producers) {
        this.event = event;
        this.producers = producers;
    }
}
