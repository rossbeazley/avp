package uk.co.rossbeazley.avp.android.videoplayer;

import java.util.ArrayList;
import java.util.Collection;

class FakeMediaPlayer implements MediaPlayer {

    private Collection<StateChangeListener> stateChangeListeners = new ArrayList<StateChangeListener>();

    private FakeMediaPlayer() {
    }

    public static MediaPlayer createFakeMediaPlayer() {
        return new FakeMediaPlayer();
    }
    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        for(StateChangeListener stateChangeListener : this.stateChangeListeners) {
            stateChangeListener.state(MediaPlayer.PREPARED);
        }
    }

    @Override
    public void addStateChangeListener(StateChangeListener stateChangeListener) {
        this.stateChangeListeners.add(stateChangeListener);
    }

    @Override
    public void start() {
        // think i should send a started state change with the createVideoView in it
        // also, it shouldnt be called a createVideoView,
    }



}
