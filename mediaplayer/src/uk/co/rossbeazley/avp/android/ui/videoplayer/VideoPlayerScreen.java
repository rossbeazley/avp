package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface VideoPlayerScreen {

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




    void tearDown();

    //TODO consider removing this, really its only tearDown that the fragment expects
    void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents);

    interface CanListenForScreenTearDownEvents {
        void screenTearDown();
        CanListenForScreenTearDownEvents NONE = new CanListenForScreenTearDownEvents() { public void screenTearDown() {} };
    }
}
