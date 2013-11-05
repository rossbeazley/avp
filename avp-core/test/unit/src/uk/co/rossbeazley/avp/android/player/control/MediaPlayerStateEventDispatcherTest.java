package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.android.player.time.FakeScheduledExecutor;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerStateEventDispatcherTest {

    private String playerState = "UNKNOWN";
    private FakeMediaPlayer mediaPlayer;
    private EventBus bus;
    private FakeScheduledExecutor fakeScheduledExecutor;

    @Before
    public void setup() {
        mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();

        bus = new ExecutorEventBus();
        bus.whenEvent(Events.PLAYER_PAUSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        playerState = Events.PLAYER_PAUSED;
                    }
                });

        bus.whenEvent(Events.PLAYER_PLAYING)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        playerState = Events.PLAYER_PLAYING;
                    }
                });


        fakeScheduledExecutor = new FakeScheduledExecutor();
        new MediaPlayerSateEventDispatcher(bus, fakeScheduledExecutor);

    }

    @Test
    public void whenTransitionsToPauseFromPlayingPauseEventRaised() {
        bus.sendPayload(mediaPlayer)
                .withEvent(Events.PLAYER_VIDEO_LOADED);
        mediaPlayer.pause();
        fakeScheduledExecutor.runOnce();
        assertThat(playerState, is(Events.PLAYER_PAUSED));

    }

    @Test
    public void whenTransitionsToPlayFromPausedPlayEventRaised() {
        mediaPlayer.pause();
        bus.sendPayload(mediaPlayer)
                .withEvent(Events.PLAYER_VIDEO_LOADED);
        mediaPlayer.start();
        fakeScheduledExecutor.runOnce();
        assertThat(playerState, is(Events.PLAYER_PLAYING));

    }

}
