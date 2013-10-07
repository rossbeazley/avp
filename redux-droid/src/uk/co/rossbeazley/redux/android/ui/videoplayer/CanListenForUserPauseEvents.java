package uk.co.rossbeazley.redux.android.ui.videoplayer;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 09/09/13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public interface CanListenForUserPauseEvents {
    void userPressedPause();

    CanListenForUserPauseEvents NONE = new CanListenForUserPauseEvents() {
        public void userPressedPause() {}
    };
}
