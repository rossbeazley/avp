package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MediaPlayerScrubbingTest {

    private FakeMediaPlayer mediaPlayer;
    private EventBus bus;

    @Before
    public void setup() {
        mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus = new ExecutorEventBus();
        bus.sendPayload(mediaPlayer)
                .withEvent(Events.PLAYER_VIDEO_LOADED);
        new MediaPlayerScrubber(bus);
    }

    @Test
    public void scrubsMediaPlayerWhenUserScrubEventReceived() {

        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(5000);

        bus.sendPayload(expectedScrubPosition)
            .withEvent(Events.USER_SCRUB);

        assertThat(mediaPlayer.seekingTo(),is(expectedScrubPosition));
    }

    @Test
    public void whenScrubbingNotFinishedUserScrubEventNotProcessed() {
        fail("not speced yet");
    }

    @Test
    public void whenScrubbingFinishedUnprocessedUserScrubEventProcessed() {
        fail("not speced yet");
    }

    @Test
    public void whenScrubbingFinishedOnlyLastUnprocessedUserScrubEventProcessed() {
        fail("not speced yet");
    }



    private class MediaPlayerScrubber {
        public MediaPlayerScrubber(EventBus bus) {

        }
    }
}
