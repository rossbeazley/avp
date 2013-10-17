package uk.co.rossbeazley.avp.android.player;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerPrepareTest implements MediaPlayerFactory {


    public static final String PREPARED = "PREPARED";
    public static final String NOT_PREPARED = "NOT PREPARED";

    private String mediaPlayerState = NOT_PREPARED;

    @Test
    public void preparesMediaPlayerAndTellsEveryone() {

        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer();

        videoPreparer.addPreparedListener(new VideoPreparer.PreparedListener() {
            @Override
            public void prepared() {
                mediaPlayerState = PREPARED;
            }
        });

        CanPrepareMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        videoPreparer.prepareMediaPlayer(mediaPlayer);

        assertThat(mediaPlayerState,is(PREPARED));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return FakeMediaPlayer.createFakeMediaPlayer();
    }
}
