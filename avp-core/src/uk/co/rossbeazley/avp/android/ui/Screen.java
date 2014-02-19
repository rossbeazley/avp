package uk.co.rossbeazley.avp.android.ui;

public interface Screen {
    void tearDown();

    void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents);

    interface CanListenForScreenTearDownEvents {
        void screenTearDown();
        CanListenForScreenTearDownEvents NONE = new CanListenForScreenTearDownEvents() { public void screenTearDown() {} };
    }
}
