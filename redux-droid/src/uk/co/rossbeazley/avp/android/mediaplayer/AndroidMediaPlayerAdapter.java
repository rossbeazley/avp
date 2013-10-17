package uk.co.rossbeazley.avp.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.videoplayer.VideoView;

import java.io.IOException;

public class AndroidMediaPlayerAdapter implements MediaPlayer {


    private final android.media.MediaPlayer mediaPlayer;

    public AndroidMediaPlayerAdapter(android.media.MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void addStateChangeListener(StateChangeListener stateChangeListener) {

    }


    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mediaPlayer.setDataSource(context, uri);
    }

    public void prepareAsync() throws IllegalStateException {
        mediaPlayer.prepareAsync();
    }

    public void start() throws IllegalStateException {
        mediaPlayer.start();
    }
}
