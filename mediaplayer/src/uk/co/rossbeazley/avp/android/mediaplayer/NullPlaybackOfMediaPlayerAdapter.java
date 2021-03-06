package uk.co.rossbeazley.avp.android.mediaplayer;

import android.view.SurfaceHolder;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.control.CanControlPlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.preparer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.player.render.CanAttachToAndroidView;
import uk.co.rossbeazley.avp.android.player.scrub.CanScrubMediaPlayer;
import uk.co.rossbeazley.avp.android.player.state.CanDiscoverPlayingStateOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.time.CanGetTimeFromMediaPlayer;

public class NullPlaybackOfMediaPlayerAdapter implements CanPrepareMediaPlayer, CanControlPlaybackOfMediaPlayer, CanGetTimeFromMediaPlayer, CanScrubMediaPlayer, CanAttachToAndroidView, CanDiscoverPlayingStateOfMediaPlayer {

    private final TimeInMilliseconds noTime = TimeInMilliseconds.fromLong(0);

    public NullPlaybackOfMediaPlayerAdapter() {
    }


    @Override
    public void start() {
        doLog("Start");
    }

    @Override
    public boolean isPlaying() {
        doLog("isPlaying");
        return false;
    }

    @Override
    public boolean isNotPlaying() {
        doLog("isNotPlaying");
        return false;
    }

    @Override
    public void stop() {
        doLog("Stop");
    }

    @Override
    public void pause() {
        doLog("Pause");
    }

    @Override
    public void prepareAsync() {
        doLog("PrepareAsync");
    }

    @Override
    public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {
        doLog("AddPreparedStateChangeListener");
    }

    @Override
    public TimeInMilliseconds getCurrentPosition() {
        doLog("GetCurrentPosition");
        return noTime;
    }

    @Override
    public TimeInMilliseconds getDuration() {
        doLog("GetDuration");
        return noTime;
    }

    @Override
    public void seekTo(TimeInMilliseconds time) {
        doLog("SeekTo");
    }

    @Override
    public void addScrubCompleteListener(ScrubCompleteListener listener) {
        doLog("AddScrubCompleteListener");
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        doLog("SetDisplay");
    }




    private void doLog(String msg) {
        //logger.debug("Null Media Player Adapter " + msg);
    }
}
