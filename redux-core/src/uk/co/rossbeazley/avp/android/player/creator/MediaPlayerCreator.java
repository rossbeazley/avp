package uk.co.rossbeazley.avp.android.player.creator;

import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;

//I could split this interface into two, creation and announcement, the tests would look neater
public interface MediaPlayerCreator {
    void create(UriString any_uri_string);

    void addCreatedListener(CreatedListener createdListener);

    interface CreatedListener {
        void created(MediaPlayer mediaPlayer);
    }
}
