package uk.co.rossbeazley.redux.android.ui.videoplayer;

import uk.co.rossbeazley.redux.TimeInMilliseconds;

public interface VideoScreen {
    void bind();

    void inflateLayout();

    void bindPauseButton();

    void bindPlayButton();

    void bindSeekBar();

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
