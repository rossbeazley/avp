package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerAutoPlayTest {

    private static final boolean PLAYING = true;
    private static final boolean STOPPED = false;
    private boolean announcedPlayerState;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new MediaPlayerAutoPlay(bus);
    }

    @Test
    public void autoStartsTheMediaPlayerWhenVideoLoaded() {
        FakeMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);
        assertThat(mediaPlayer.isPlaying(), is(true));
    }

    @Test
    public void whenMediaPlayerStartedEventAnnounced() {

        announcedPlayerState = STOPPED;
        bus.whenEvent(Events.PLAYER_PLAYING).thenRun(new Function() {
            @Override
            public void invoke() {
                announcedPlayerState = PLAYING;
            }
        });

        FakeMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);
        assertThat(announcedPlayerState, is(PLAYING));   //SMELL this object should not send this event, responsibility to be moved to a different object
    }
}
