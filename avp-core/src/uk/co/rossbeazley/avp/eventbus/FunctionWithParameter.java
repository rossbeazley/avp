package uk.co.rossbeazley.avp.eventbus;

public interface FunctionWithParameter<T> {
    void invoke(T payload);
}
