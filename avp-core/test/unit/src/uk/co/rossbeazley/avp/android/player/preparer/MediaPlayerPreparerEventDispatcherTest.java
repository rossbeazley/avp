package uk.co.rossbeazley.avp.android.player.preparer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanPrepareMediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerPreparerEventDispatcherTest {

    private CanPrepareMediaPlayer preparedMediaPlayer = null;

    @Test
    public void eventBusAnnouncmentCausesMediaPlayerCreation() {
        EventBus bus = new ExecutorEventBus();

        MediaPlayerPreparer mediaPlayerPreparer = new MediaPlayerPreparer() {
            public void addPreparedListener(PreparedListener preparedListener) {}

            public void prepareMediaPlayer(CanPrepareMediaPlayer mediaPlayer) {
                preparedMediaPlayer = mediaPlayer;
            }
        };
        new MediaPlayerPreparerEventDispatcher(bus, mediaPlayerPreparer);

        MediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        bus.sendPayload(expectedMediaPlayer).withEvent(Events.MEDIA_PLAYER_CREATED);

        assertThat(preparedMediaPlayer == expectedMediaPlayer,is(true));
    }

}
