package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class FakeVideoScreen implements VideoScreen {

    public boolean showPause;
    public boolean hideBuffering;
    private CanListenForUserPauseEvents canListenForUserPauseEvents;
    private CanListenForUserPlayEvents canListenForUserPlayEvents;
    private CanListenForUserScrubEvents canListenForUserScrubEvents;

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
    }

    @Override
    public void showProgressTime(TimeInMilliseconds time) {
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
