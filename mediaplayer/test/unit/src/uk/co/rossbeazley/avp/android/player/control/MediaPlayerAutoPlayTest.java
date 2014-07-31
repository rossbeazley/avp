package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.player.FakePlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerAutoPlayTest {

    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new MediaPlayerAutoPlay(bus);
    }

    @Test
    public void autoStartsTheMediaPlayerWhenVideoLoaded() {
        FakePlaybackOfMediaPlayer mediaPlayer = FakePlaybackOfMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED);
        assertThat(mediaPlayer.isPlaying(), is(true));
    }
}
