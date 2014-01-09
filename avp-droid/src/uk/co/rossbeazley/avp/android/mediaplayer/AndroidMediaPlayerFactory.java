package uk.co.rossbeazley.avp.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;

import java.io.IOException;

public class AndroidMediaPlayerFactory implements MediaPlayerFactory {

    private Context applicationContext;
    private final Logger logger;

    public AndroidMediaPlayerFactory(Context applicationContext, Logger logger) {
        this.applicationContext = applicationContext;
        this.logger = logger;
    }

    @Override
    public Object createMediaPlayerForUri(UriString uri) {
        Object result;
        try {
            android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
            mediaPlayer.setDataSource(applicationContext,Uri.parse(uri.uri));
            result = new AndroidPlaybackOfMediaPlayerAdapter(mediaPlayer, logger);
        } catch (IOException e) {
            result = new NullPlaybackOfMediaPlayerAdapter(logger);
        }
        return result;
    }
}
