package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface VideoControlScreen {
    void bind();

    void showTotalTime(TimeInMilliseconds time);

    void showProgressTime(TimeInMilliseconds time);

    void showBuffering();

    void hideBuffering();

    void showPlay();

    void showPause();


    void setPauseEventListener(CanListenForUserPauseEvents canListenForUserPauseEvents);

    void setPlayEventListener(CanListenForUserPlayEvents canListenForUserPlayEvents);

    void setScrubEventListener(CanListenForUserScrubEvents canListenForUserScrubEvents);

    void showSeekBarPosition(long position, long max);

    interface CanListenForUserPauseEvents {
        void userPressedPause();
        CanListenForUserPauseEvents NONE = new CanListenForUserPauseEvents() { public void userPressedPause() {} };
    }

    interface CanListenForUserPlayEvents {
        void userPressedPlay();
        CanListenForUserPlayEvents NONE = new CanListenForUserPlayEvents() { public void userPressedPlay() {} };
    }

    interface CanListenForUserScrubEvents {
        void userScrubbedTo(long positionAsPercentage);
        CanListenForUserScrubEvents NONE = new CanListenForUserScrubEvents() { public void userScrubbedTo(long positionAsPercentage) {} };
    }
}
