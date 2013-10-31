package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class FakeVideoScreenVideo implements VideoControlScreen {

    public boolean showPlay = false;
    public boolean showPause = false;
    public boolean hideBuffering = false;
    private CanListenForUserPauseEvents canListenForUserPauseEvents;
    private CanListenForUserPlayEvents canListenForUserPlayEvents;
    private CanListenForUserScrubEvents canListenForUserScrubEvents;
    public TimeInMilliseconds progressTime;
    public TimeInMilliseconds totalTime;

    @Override
    public void bind() {
    }

    @Override
    public void setPauseEventListener(CanListenForUserPauseEvents canListenForUserPauseEvents) {
        this.canListenForUserPauseEvents = canListenForUserPauseEvents;
    }

    @Override
    public void setPlayEventListener(CanListenForUserPlayEvents canListenForUserPlayEvents) {
        this.canListenForUserPlayEvents = canListenForUserPlayEvents;
    }

    @Override
    public void setScrubEventListener(CanListenForUserScrubEvents canListenForUserScrubEvents) {
        this.canListenForUserScrubEvents = canListenForUserScrubEvents;
    }

    @Override
    public void showTotalTime(TimeInMilliseconds time) {
        totalTime = time;
    }

    @Override
    public void showProgressTime(TimeInMilliseconds time) {
        progressTime = time;
    }

    @Override
    public void showBuffering() {
    }

    @Override
    public void hideBuffering() {
        hideBuffering = true;
    }

    @Override
    public void showPlay() {
        showPlay = true;
    }

    @Override
    public void showPause() {
        showPause = true;
    }

    @Override
    public void attachVideo(RenderedVideoOutput videoOutput) {
    }

    public void pausePressed() {
        canListenForUserPauseEvents.userPressedPause();
    }

    public void playPressed() {
        canListenForUserPlayEvents.userPressedPlay();
    }

    public void scrubTo(TimeInMilliseconds expectedScrubTime) {
        canListenForUserScrubEvents.userScrubbedTo(expectedScrubTime);
    }
}
