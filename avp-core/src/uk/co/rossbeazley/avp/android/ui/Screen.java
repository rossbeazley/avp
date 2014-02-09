package uk.co.rossbeazley.avp.android.ui;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 23/01/2014
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public interface Screen {
    void tearDown();

    void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents);

    interface CanListenForScreenTearDownEvents {
        void screenTearDown();
        CanListenForScreenTearDownEvents NONE = new CanListenForScreenTearDownEvents() { public void screenTearDown() {} };
    }
}
