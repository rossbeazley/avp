package uk.co.rossbeazley.redux.eventbus;

public interface Function {
    void invoke();

    Function NOOP = new Function() {
        public void invoke() { }
    };
}
