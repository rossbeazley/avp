package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class AndroidMediaPlayerCreator implements MediaPlayerCreator {

    private final MediaPlayerFactory mediaPlayerFactory;
    private Collection<CreatedListener> createdListeners;

    public AndroidMediaPlayerCreator(MediaPlayerFactory mediaPlayerFactory) {
        this.mediaPlayerFactory = mediaPlayerFactory;
        this.createdListeners = new ArrayList<CreatedListener>();
    }

    @Override
    public void addCreatedListener(CreatedListener createdListener) {
        this.createdListeners.add(createdListener);
    }

    @Override
    public void create(UriString any_uri_string) {
        MediaPlayer mediaplayer = this.mediaPlayerFactory.createMediaPlayerForUri(any_uri_string);
        for (CreatedListener createdListener : this.createdListeners) {
            createdListener.created(mediaplayer);
        }
    }
}
