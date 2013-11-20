package uk.co.rossbeazley.avp.android.player.preparer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerPreparerTest {

    private FakeMediaPlayer preparedMediaPlayer = null;


    @Test
    public void tellsEveryoneAboutPreparedMediaPlayer() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerPreparer(bus);
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<FakeMediaPlayer>() {
            @Override
            public void invoke(FakeMediaPlayer payload) {
                preparedMediaPlayer = payload;
            }
        });

        FakeMediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(expectedMediaPlayer).withEvent(Events.PLAYER_CREATED);

        assertThat(preparedMediaPlayer.isPrepared(), is(true));
    }

}
