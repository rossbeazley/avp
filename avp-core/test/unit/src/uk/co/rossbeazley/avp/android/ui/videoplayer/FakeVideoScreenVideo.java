package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

class FakeVideoScreenVideo implements VideoPlayerScreen {

    public boolean showPlay = false;
    public boolean showPause = false;
    public boolean hideBuffering = false;
    private CanListenForUserPauseEvents canListenForUserPauseEvents;
    private CanListenForUserPlayEvents canListenForUserPlayEvents;
    private CanListenForUserScrubEvents canListenForUserScrubEvents;
    public TimeInMilliseconds progressTime;
    public TimeInMilliseconds totalTime;
    private long scrubBarMax;
    private long scrubBarPosition;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;

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
    public void showSeekBarPosition(long position, long max) {
        scrubBarMax = max;
        scrubBarPosition = position;
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

    public void pausePressed() {
        canListenForUserPauseEvents.userPressedPause();
    }

    public void playPressed() {
        canListenForUserPlayEvents.userPressedPlay();
    }

    public void scrubTo(long positionAsPercentage) {
        canListenForUserScrubEvents.userScrubbedTo(positionAsPercentage);
    }

    public long scrubBarMax() {
        return scrubBarMax;
    }

    public long scrubBarPosition() {
        return scrubBarPosition;
    }

    @Override
    public void tearDown() {
        canListenForScreenTearDownEvents.screenTearDown();
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        this.canListenForScreenTearDownEvents = canListenForScreenTearDownEvents;
    }
}
