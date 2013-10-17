package uk.co.rossbeazley.avp.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.avp.UriString;

public class AndroidMediaPlayerFactory implements MediaPlayerFactory {

    private Context applicationContext;

    public AndroidMediaPlayerFactory(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        android.media.MediaPlayer mediaPlayer = android.media.MediaPlayer.create(applicationContext, Uri.parse(uri.uri));
        AndroidMediaPlayerAdapter androidMediaPlayerAdapter = new AndroidMediaPlayerAdapter(mediaPlayer);
        return androidMediaPlayerAdapter;
    }
}
