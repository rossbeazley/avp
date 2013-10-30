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

public class MediaPlayerControlTest {

    private static final boolean STOPPED = true;
    private static final boolean PLAYING = false;
    private static final boolean PAUSED = true;
    private boolean playerState;
    private EventBus bus;
    private FakeMediaPlayer mediaPlayer;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new MediaPlayerControl(bus);
        mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED); //thoughts, should these mediaplayer satalite objects be constructed with a media player rather that recieve one over the bus?

    }

    @Test
    public void stopsTheMediaPlayerWhenAppHidden() {
        bus.announce(Events.APP_HIDDEN);
        assertThat(mediaPlayer.isStopped(), is(true));
    }

    @Test
    public void announcesStopEventWhenTheMediaPlayerIsStopped() {
        playerState = PLAYING;
        bus.whenEvent(Events.PLAYER_STOPPED).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState = STOPPED;
            }
        });
        bus.announce(Events.APP_HIDDEN);
        assertThat(playerState, is(STOPPED)); //SMELL this class is responsible for sending stop events, to be moved out into a mediaplayer state watcher thingy class
    }

    @Test
    public void pausesTheMediaPlayerOnPauseEvent() {
        bus.announce(Events.PAUSE);
        assertThat(mediaPlayer.isPaused(),is(true));
    }

    @Test
    public void whenPausedPlayerPausedEventRaised() {
        playerState = PLAYING;
        bus.whenEvent(Events.PLAYER_PAUSED).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState=PAUSED;
            }
        });
        bus.announce(Events.PAUSE);
        assertThat(playerState,is(PAUSED));
    }

    @Test
    public void playsTheMediaPlayerOnPlayEvent() {
        mediaPlayer.pause();
        bus.announce(Events.PLAY);
        assertThat(mediaPlayer.isPlaying(),is(true));
    }

    @Test
    public void whenPlayingFromPausedPlayerPlayingEventRaised() {
        mediaPlayer.pause();
        playerState = PAUSED;
        bus.whenEvent(Events.PLAYER_PLAYING).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState=PLAYING;
            }
        });

        bus.announce(Events.PLAY);
        assertThat(playerState, is(PLAYING));
    }
}
