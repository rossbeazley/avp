package uk.co.rossbeazley.redux.eventbus;

public interface FunctionWithParameter<T> {
    void invoke(T object);
}
