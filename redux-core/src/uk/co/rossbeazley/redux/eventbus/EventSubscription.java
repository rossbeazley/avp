package uk.co.rossbeazley.redux.eventbus;

public interface EventSubscription {
    void thenRun(Function fn);

    void thenRun(FunctionWithParameter fn);
}
