package uk.co.rossbeazley.avp.eventbus;

public interface EventSubscription {
    void thenRun(Function fn);

    void thenRun(FunctionWithParameter fn);
}
