package uk.co.rossbeazley.avp.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.videoplayer.MediaPlayerFactory;

import java.io.IOException;

public class AndroidMediaPlayerFactory implements MediaPlayerFactory {

    private Context applicationContext;

    public AndroidMediaPlayerFactory(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {

        android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
        AndroidMediaPlayerAdapter androidMediaPlayerAdapter = new AndroidMediaPlayerAdapter(mediaPlayer);
        try {
            androidMediaPlayerAdapter.setDataSource(applicationContext, Uri.parse(uri.uri));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return androidMediaPlayerAdapter;
    }
}
