package uk.co.rossbeazley.avp.android.player.preparer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class MediaPlayerPrepareDelegateTest implements MediaPlayerFactory {

    private CanPrepareMediaPlayer preparedMediaPlayer;
    @Test
    public void preparesMediaPlayerAndTellsEveryone() {


        MediaPlayerPreparerDelegate.PreparedListener preparedListener = new MediaPlayerPreparerDelegate.PreparedListener() {
            @Override
            public void prepared(CanPrepareMediaPlayer payload) {
                preparedMediaPlayer = payload;
            }
        };
        MediaPlayerPreparerDelegate videoPreparer = new MediaPlayerPreparerDelegate();

        CanPrepareMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        videoPreparer.prepareMediaPlayer(mediaPlayer, preparedListener);

        assertThat(preparedMediaPlayer,is(mediaPlayer));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return FakeMediaPlayer.createFakeMediaPlayer();
    }
}
