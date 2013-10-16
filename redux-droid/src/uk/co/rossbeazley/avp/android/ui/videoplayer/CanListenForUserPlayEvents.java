package uk.co.rossbeazley.avp.android.ui.videoplayer;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 09/09/13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public interface CanListenForUserPlayEvents {

    void userPressedPlay();

    static final CanListenForUserPlayEvents NONE = new CanListenForUserPlayEvents() {
        public void userPressedPlay() {}
    };
}
