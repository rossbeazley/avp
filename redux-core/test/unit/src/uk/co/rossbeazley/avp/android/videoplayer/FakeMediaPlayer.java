package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;

import java.util.ArrayList;
import java.util.Collection;

class FakeMediaPlayer implements MediaPlayer {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();

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
        // think i should send a started state change
    }



}
