package uk.co.rossbeazley.avp.android.player.preparer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakePlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerPreparerTest {

    private FakePlaybackOfMediaPlayer preparedMediaPlayer = null;


    @Test
    public void tellsEveryoneAboutPreparedMediaPlayer() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerPreparer(bus);
        bus.whenEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<FakePlaybackOfMediaPlayer>() {
            @Override
            public void invoke(FakePlaybackOfMediaPlayer payload) {
                preparedMediaPlayer = payload;
            }
        });

        FakePlaybackOfMediaPlayer expectedMediaPlayer = FakePlaybackOfMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(expectedMediaPlayer).withEvent(Events.PLAYER_CREATED);

        assertThat(preparedMediaPlayer.isPrepared(), is(true));
    }

}
