package uk.co.rossbeazley.avp.android.player;

import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class FakeMediaPlayer implements MediaPlayer {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();
    private boolean playing;
    private boolean paused;

    private FakeMediaPlayer() {
    }

    public static FakeMediaPlayer createFakeMediaPlayer() {
        return new FakeMediaPlayer();
    }
    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        for(PreparedStateChangeListener preparedStateChangeListener : this.preparedStateChangeListeners) {
            preparedStateChangeListener.state(MediaPlayer.PREPARED);
        }
    }

    @Override
    public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {
        this.preparedStateChangeListeners.add(preparedStateChangeListener);
    }

    @Override
    public void start() {
        playing=true;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public boolean isStopped() {
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
}
