package uk.co.rossbeazley.avp.android.player.state;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerStateEventDispatcherTest {

    private String playerState = "UNKNOWN";

    @Test
    public void whenTransitionsToPauseFromPlayingPauseEventRaised() {

        FakeMediaPlayer mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();

        EventBus bus = new ExecutorEventBus();
        bus.whenEvent(Events.PLAYER_PAUSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        playerState = Events.PLAYER_PAUSED;
                    }
                });


        new MediaPlayerSateEventDispatcher(mediaPlayer,bus);

        mediaPlayer.pause();

        assertThat(playerState, is(Events.PLAYER_PAUSED));

    }

    private class MediaPlayerSateEventDispatcher {
        public MediaPlayerSateEventDispatcher(FakeMediaPlayer mediaPlayer, EventBus bus) {

        }
    }
}
