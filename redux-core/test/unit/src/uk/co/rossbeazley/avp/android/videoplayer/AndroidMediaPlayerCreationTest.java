package uk.co.rossbeazley.avp.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class AndroidMediaPlayerCreationTest implements MediaPlayerFactory {


    private MediaPlayer fakeMediaPlayer;
    private MediaPlayer announcedMediaPlayer;

    @Test
    public void createsMediaPlayerAndTellsEveryone() {

        fakeMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        MediaPlayerCreator creator = new MediaPlayerCreator(this);

        creator.addCreatedListener(new CreatedListener(){
            @Override
            public void created(MediaPlayer mediaPlayer) {
                announcedMediaPlayer = mediaPlayer;
            }
        });

        UriString any_uri_string = UriString.from("ANY");
        creator.create(any_uri_string);

        assertThat(fakeMediaPlayer, is(announcedMediaPlayer));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return fakeMediaPlayer;
    }

    private class MediaPlayerCreator {

        private final MediaPlayerFactory mediaPlayerFactory;
        private Collection<CreatedListener> createdListeners;

        public MediaPlayerCreator(MediaPlayerFactory mediaPlayerFactory) {
            this.mediaPlayerFactory = mediaPlayerFactory;
            createdListeners = new ArrayList<CreatedListener>();
        }

        public void addCreatedListener(CreatedListener createdListener) {
            this.createdListeners.add(createdListener);
        }

        public void create(UriString any_uri_string) {
            MediaPlayer mediaplayer = mediaPlayerFactory.createMediaPlayerForUri(any_uri_string);
            for (CreatedListener createdListener : createdListeners) {
                createdListener.created(mediaplayer);
            }

        }
    }

    private interface CreatedListener {
        void created(MediaPlayer mediaPlayer);
    }
}
