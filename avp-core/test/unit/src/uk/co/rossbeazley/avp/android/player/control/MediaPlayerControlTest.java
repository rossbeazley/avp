package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerControlTest {

    private static final boolean STOPPED = true;
    private static final boolean STARTED = false;
    private boolean playerState;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new MediaPlayerControl(bus);
    }

    @Test
    public void stopsTheMediaPlayerWhenAppHidden() {

        FakeMediaPlayer mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED); //thoughts, should these mediaplayer satalite objects be constructed with a media player rather that recieve one over the bus?
        bus.announce(Events.APP_HIDDEN);
        assertThat(mediaPlayer.isStopped(), is(true));
    }

    @Test
    public void announcesStopEventWhenTheMediaPlayerIsStopped() {

        CanControlMediaPlayer mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED); //thoughts, should these mediaplayer satalite objects be constructed with a media player rather that recieve one over the bus?

        playerState = STARTED;

        bus.whenEvent(Events.PLAYER_STOPPED).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState = STOPPED;
            }
        });

        bus.announce(Events.APP_HIDDEN);

        assertThat(playerState, is(STOPPED)); //SMELL dont this this class is responsible for sending stop events, to be moved out
    }

    @Test
    public void pausesTheMediaPlayerOnPauseEvent() {
        FakeMediaPlayer mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED);
        bus.announce(Events.PAUSE);
        assertThat(mediaPlayer.isPaused(),is(true));
    }
}
