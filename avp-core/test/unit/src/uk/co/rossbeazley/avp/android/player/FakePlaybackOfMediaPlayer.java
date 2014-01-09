package uk.co.rossbeazley.avp.android.player;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.control.CanControlPlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.preparer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.player.scrub.CanScrubMediaPlayer;
import uk.co.rossbeazley.avp.android.player.state.CanDiscoverPlayingStateOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.time.CanGetTimeFromMediaPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class FakePlaybackOfMediaPlayer implements CanPrepareMediaPlayer,
                                                  CanControlPlaybackOfMediaPlayer,
                                                  CanGetTimeFromMediaPlayer,
                                                  CanScrubMediaPlayer,
                                                  CanDiscoverPlayingStateOfMediaPlayer {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();
    private boolean playing;
    private boolean paused;
    private boolean prepared;
    private TimeInMilliseconds currentPosition;
    private TimeInMilliseconds duration;
    private TimeInMilliseconds scrubToTime;
    private Collection<ScrubCompleteListener> seekCompleteListeners = new ArrayList<ScrubCompleteListener>();

    private FakePlaybackOfMediaPlayer() {
    }

    public static FakePlaybackOfMediaPlayer createFakeMediaPlayer() {
        return new FakePlaybackOfMediaPlayer();
    }

    public static FakePlaybackOfMediaPlayer createStartedFakeMediaPlayer() {
        FakePlaybackOfMediaPlayer fakeMediaPlayer = createFakeMediaPlayer();
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

    public void announceScrubbingComplete() {
        for (ScrubCompleteListener seekCompleteListener : seekCompleteListeners) {
            seekCompleteListener.seekComplete();
        }

    }

    @Override
    public void addScrubCompleteListener(ScrubCompleteListener listener) {
        this.seekCompleteListeners.add(listener);
    }


}
