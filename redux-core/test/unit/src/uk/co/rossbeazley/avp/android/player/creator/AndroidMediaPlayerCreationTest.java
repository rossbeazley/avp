package uk.co.rossbeazley.avp.android.player.creator;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerCreationTest implements MediaPlayerFactory {


    private MediaPlayer fakeMediaPlayer;
    private MediaPlayer announcedMediaPlayer;

    @Test
    public void createsMediaPlayerAndTellsEveryone() {

        fakeMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        MediaPlayerCreator creator = new AndroidMediaPlayerCreator(this);

        creator.addCreatedListener(new MediaPlayerCreator.CreatedListener(){
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

}
