package uk.co.rossbeazley.avp.android.mediaplayer;

import android.content.Context;
import android.net.Uri;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.io.IOException;

public class AndroidMediaPlayerFactory implements MediaPlayerFactory {

    public static final String PLAYER_CREATED = "android_media_player_created";
    private Context applicationContext;
    private final EventBus eventBus;

    public AndroidMediaPlayerFactory(Context applicationContext, EventBus eventBus) {
        this.applicationContext = applicationContext;
        this.eventBus = eventBus;
    }

    @Override
    public Object createMediaPlayerForUri(String uri) {
        Object result;
        try {
            android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();
            mediaPlayer.setDataSource(applicationContext,Uri.parse(uri));
            result = new AndroidPlaybackOfMediaPlayerAdapter(mediaPlayer );

            eventBus.sendPayload(result).withEvent(AndroidMediaPlayerFactory.PLAYER_CREATED);

        } catch (IOException e) {
            result = new NullPlaybackOfMediaPlayerAdapter();
        }
        return result;
    }
}
