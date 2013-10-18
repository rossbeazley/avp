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

public class MediaPlayerPreparerEventBusIntegratedTest {

    private MediaPlayer preparedMediaPlayer = null;

    @Test
    public void preparesMediaPlayerThenTellsEveryone() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerPreparerEventDispatcher(bus);
        bus.whenEvent(Events.VIDEO_LOADED).thenRun(new FunctionWithParameter<MediaPlayer>() {
            @Override
            public void invoke(MediaPlayer payload) {
                preparedMediaPlayer = payload;
            }
        });

        MediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(expectedMediaPlayer).withEvent(Events.MEDIA_PLAYER_CREATED);

        assertThat(preparedMediaPlayer, is(expectedMediaPlayer));
    }

}
