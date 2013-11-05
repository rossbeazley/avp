package uk.co.rossbeazley.avp.android.player.preparer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerPreparerTest {

    private MediaPlayer preparedMediaPlayer = null;

    @Test
    public void preparesANewlyCreatedMediaPlayer() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerPreparer(bus);
        FakeMediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(expectedMediaPlayer).withEvent(Events.PLAYER_CREATED);

        assertThat(expectedMediaPlayer.isPrepared(), is(true));
    }

    @Test
    public void tellsEveryoneAboutPreparedMediaPlayer() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerPreparer(bus);
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<MediaPlayer>() {
            @Override
            public void invoke(MediaPlayer payload) {
                preparedMediaPlayer = payload;
            }
        });

        MediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(expectedMediaPlayer).withEvent(Events.PLAYER_CREATED);

        assertThat(preparedMediaPlayer, is(expectedMediaPlayer));
    }

}
