package uk.co.rossbeazley.redux.android.ui.videoplayer;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 09/09/13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public interface CanListenForUserScrubEvents {
    void userScrubbedTo(int time);

    CanListenForUserScrubEvents NONE = new CanListenForUserScrubEvents() {
        public void userScrubbedTo(int time) {        }
    };
}
