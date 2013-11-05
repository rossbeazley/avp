package uk.co.rossbeazley.avp.android.player;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class FakeMediaPlayer implements MediaPlayer {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();
    private boolean playing;
    private boolean paused;
    private boolean prepared;
    private TimeInMilliseconds currentPosition;
    private TimeInMilliseconds duration;
    private TimeInMilliseconds scrubToTime;

    private FakeMediaPlayer() {
    }

    public static FakeMediaPlayer createFakeMediaPlayer() {
        return new FakeMediaPlayer();
    }

    public static FakeMediaPlayer createStartedFakeMediaPlayer() {
        FakeMediaPlayer fakeMediaPlayer = createFakeMediaPlayer();
        fakeMediaPlayer.setCurrentPosition(TimeInMilliseconds.fromLong(0));
        fakeMediaPlayer.setDuration(TimeInMilliseconds.fromLong(40000));
        fakeMediaPlayer.start();
        return fakeMediaPlayer;
    }
    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        prepared=true;
        for(PreparedStateChangeListener preparedStateChangeListener : this.preparedStateChangeListeners) {
            preparedStateChangeListener.prepared();
        }
    }

    @Override
    public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {
        this.preparedStateChangeListeners.add(preparedStateChangeListener);
    }

    @Override
    public void start() {
        playing=true;
        paused=false;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public boolean isNotPlaying() {
        return !playing;
    }

    @Override
    public void stop() {
        playing=false;

    }

    @Override
    public void pause() {
        paused = true;
        playing = false;
    }

    public boolean isPaused() {
        return paused;  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean isPrepared() {
        return prepared;
    }

    @Override
    public TimeInMilliseconds getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(TimeInMilliseconds currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public TimeInMilliseconds getDuration() {
        return this.duration;
    }

    public void setDuration(TimeInMilliseconds duration) {
        this.duration = duration;
    }

    public TimeInMilliseconds seekingTo() {
        return scrubToTime;
    }

    @Override
    public void seekTo(TimeInMilliseconds time) {
        this.scrubToTime = time;
    }
}
