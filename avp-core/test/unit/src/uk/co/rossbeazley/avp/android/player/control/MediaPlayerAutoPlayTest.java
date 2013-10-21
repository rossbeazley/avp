package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerAutoPlayTest {

    private static final boolean PLAYING = true;
    private static final boolean STOPPED = false;
    private boolean playerState;

    @Test
    public void autoStartsTheMediaPlayerWhenVideoLoaded() {

        EventBus bus = new ExecutorEventBus();

        new MediaPlayerAutoPlay(bus);

        CanControlMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        bus.whenEvent(Events.PLAYER_PLAYING).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState = PLAYING;
            }
        });

        playerState = STOPPED;
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED);

        assertThat(playerState, is(PLAYING));
    }
}
