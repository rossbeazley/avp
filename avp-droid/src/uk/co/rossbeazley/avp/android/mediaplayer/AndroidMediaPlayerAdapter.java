package uk.co.rossbeazley.avp.android.mediaplayer;

import uk.co.rossbeazley.avp.android.log.Logger;

import java.util.ArrayList;
import java.util.Collection;

public class AndroidMediaPlayerAdapter implements MediaPlayer {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();
    private final android.media.MediaPlayer mediaPlayer;
    private final Logger logger;

    public AndroidMediaPlayerAdapter(android.media.MediaPlayer mediaPlayer, Logger logger) {
        this.mediaPlayer = mediaPlayer;
        this.logger = logger;
        this.mediaPlayer.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(android.media.MediaPlayer mp) {
                for(PreparedStateChangeListener preparedStateChangeListener : preparedStateChangeListeners) {
                    preparedStateChangeListener.state(MediaPlayer.PREPARED);
                }
            }
        });
    }

    @Override
    public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {
        preparedStateChangeListeners.add(preparedStateChangeListener);
    }

    public void prepareAsync() throws IllegalStateException {
        logger.debug("prepare async");
        mediaPlayer.prepareAsync();
    }

    public void start() throws IllegalStateException {
        logger.debug("start");
        mediaPlayer.start();
    }

    @Override
    public boolean isPlaying() {
        logger.debug("isPlaying? " + mediaPlayer.isPlaying());
        return mediaPlayer.isPlaying();
    }



    @Override
    public boolean isStopped() {
        return !mediaPlayer.isPlaying();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();

    }
}
