package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

public interface VideoScreen {
    void bind();

    void setPauseEventListener(CanListenForUserPauseEvents canListenForUserPauseEvents);

    void setPlayEventListener(CanListenForUserPlayEvents canListenForUserPlayEvents);

    void setScrubEventListener(CanListenForUserScrubEvents canListenForUserScrubEvents);

    void showTotalTime(TimeInMilliseconds time);

    void showProgressTime(TimeInMilliseconds time);

    void showBuffering();

    void hideBuffering();

    void showPlay();

    void showPause();

    void attachVideo(RenderedVideoOutput videoOutput);
}
