package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.FakePlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerScreenPresenter;
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
    private FakePlaybackOfMediaPlayer mediaPlayer;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new MediaPlayerControl(bus);
        mediaPlayer = FakePlaybackOfMediaPlayer.createStartedFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED); //thoughts, should these mediaplayer satalite objects be constructed with a media player rather that receive one over the bus?

    }

    @Test
    public void stopsTheMediaPlayerWhenAppHidden() {
        bus.announce(Events.APP_HIDDEN);
        assertThat(mediaPlayer.isNotPlaying(), is(true));
    }

    @Test
    public void announcesStopEventWhenTheMediaPlayerIsStopped() {
        playerState = PLAYING;
        bus.whenEvent(MediaPlayerControl.PLAYER_STOPPED).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState = STOPPED;
            }
        });
        bus.announce(Events.APP_HIDDEN);
        assertThat(playerState, is(STOPPED)); //SMELL this class is responsible for sending stop events, to be moved out into a mediaplayer prepared watcher thingy class
    }

    @Test
    public void pausesTheMediaPlayerOnPauseEvent() {
        bus.announce(VideoPlayerScreenPresenter.USER_PAUSE);
        assertThat(mediaPlayer.isPaused(),is(true));
    }

    @Test
    public void playsTheMediaPlayerOnPlayEvent() {
        mediaPlayer.pause();
        bus.announce(VideoPlayerScreenPresenter.USER_PLAY);
        assertThat(mediaPlayer.isPlaying(),is(true));
    }

    @Test
    public void stopsTheMediaPlayerWhenUserExitVideoScreen() {
        bus.announce(Events.USER_EXIT_VIDEO_SCREEN);
        assertThat(mediaPlayer.isNotPlaying(), is(true));
    }
}
