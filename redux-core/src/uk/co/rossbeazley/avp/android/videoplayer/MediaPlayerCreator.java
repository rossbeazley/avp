package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;

public interface MediaPlayerCreator {
    void create(UriString any_uri_string);

    void addCreatedListener(CreatedListener createdListener);

    interface CreatedListener {
        void created(MediaPlayer mediaPlayer);
    }
}
