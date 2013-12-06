package uk.co.rossbeazley.avp.android.mediaplayer;

import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.render.CanAttachToAndroidView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UNTESTED CLASS
 */
class AndroidMediaPlayerAdapter implements  CanPrepareMediaPlayer,
                                            CanControlMediaPlayer,
                                            CanGetTimeFromMediaPlayer,
                                            CanScrubMediaPlayer,
                                            CanAttachToAndroidView {

    private Collection<PreparedStateChangeListener> preparedStateChangeListeners = new ArrayList<PreparedStateChangeListener>();
    private final android.media.MediaPlayer mediaPlayer;
    private final Logger logger;
    private Collection<ScrubCompleteListener> seekCompleteListeners = new ArrayList<ScrubCompleteListener>();

    AndroidMediaPlayerAdapter(android.media.MediaPlayer mediaPlayer, Logger logger) {
        this.mediaPlayer = mediaPlayer;
        this.logger = logger;
        bindPreparedEventAdapter();
        bindSeekCompleteAdapter();
    }

    private void bindPreparedEventAdapter() {
        this.mediaPlayer.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(android.media.MediaPlayer mp) {
                for (PreparedStateChangeListener preparedStateChangeListener : preparedStateChangeListeners) {
                    preparedStateChangeListener.prepared();
                }
            }
        });
    }

    private void bindSeekCompleteAdapter() {
        this.mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                for(ScrubCompleteListener listener : seekCompleteListeners) {
                    listener.seekComplete();
                }
            }
        });
    }

    @Override
    public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {
        logger.debug("addPreparedStateChangeListener");
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
        boolean result = mediaPlayer.isPlaying();
        logger.debug("isPlaying? " + result);
        return result;
    }

    @Override
    public boolean isNotPlaying() {
        boolean result = !mediaPlayer.isPlaying();
        logger.debug("isNotPlaying? " + result);
        return result;
    }

    @Override
    public void stop() {
        logger.debug("stop");
        mediaPlayer.stop();
    }

    @Override
    public void pause() {
        logger.debug("pause");
        mediaPlayer.pause();
    }

    @Override
    public TimeInMilliseconds getCurrentPosition() {
        TimeInMilliseconds result;
        int millisecondsAsInt = mediaPlayer.getCurrentPosition();
        result = TimeInMilliseconds.fromLong(millisecondsAsInt);
        return result;
    }

    @Override
    public TimeInMilliseconds getDuration() {
        TimeInMilliseconds result;
        int millisecondsAsInt = mediaPlayer.getDuration();
        result = TimeInMilliseconds.fromInt(millisecondsAsInt);
        return result;
    }

    @Override
    public void seekTo(TimeInMilliseconds time) {
        logger.debug("seekTo " + time.value);
        mediaPlayer.seekTo((int) time.value);
    }

    @Override
    public void addScrubCompleteListener(ScrubCompleteListener listener) {
        this.seekCompleteListeners.add(listener);
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        logger.debug("Set Display");
        mediaPlayer.setDisplay(surfaceHolder);
    }
}
