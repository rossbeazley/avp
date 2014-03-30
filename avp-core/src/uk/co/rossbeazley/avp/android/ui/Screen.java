package uk.co.rossbeazley.avp.android.ui;

public interface Screen {
    void tearDown();

    //TODO consider removing this, really its only tearDown that the fragment expects
    void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents);

    interface CanListenForScreenTearDownEvents {
        void screenTearDown();
        CanListenForScreenTearDownEvents NONE = new CanListenForScreenTearDownEvents() { public void screenTearDown() {} };
    }
}
