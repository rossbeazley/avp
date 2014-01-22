package uk.co.rossbeazley.avp.android.player.scrub;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.FakePlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerScrubbingTest {

    private FakePlaybackOfMediaPlayer mediaPlayer;
    private EventBus bus;

    @Before
    public void setup() {
        mediaPlayer = FakePlaybackOfMediaPlayer.createStartedFakeMediaPlayer();
        bus = new ExecutorEventBus();
        new MediaPlayerScrubber(bus);
        bus.sendPayload(mediaPlayer)
                .withEvent(Events.PLAYER_VIDEO_LOADED);
    }

    @Test
    public void scrubsMediaPlayerWhenUserScrubEventReceived() {

        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(5000);

        bus.sendPayload(expectedScrubPosition)
                .withEvent(Events.USER_SCRUB);

        assertThat(mediaPlayer.seekingTo(), is(expectedScrubPosition));
    }

    @Test
    public void whenScrubbingNotFinishedUserScrubEventNotProcessed() {
        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(5000);

        TimeInMilliseconds secondScrubPosition = TimeInMilliseconds.fromLong(15000);

        bus.sendPayload(expectedScrubPosition)
                .withEvent(Events.USER_SCRUB);

        bus.sendPayload(secondScrubPosition)
                .withEvent(Events.USER_SCRUB);

        assertThat(mediaPlayer.seekingTo(), is(expectedScrubPosition));
    }

    @Test
    public void whenScrubbingFinishedPendingUserScrubEventProcessed() {

        TimeInMilliseconds firstScrubPosition = TimeInMilliseconds.fromLong(300);

        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(2300);

        bus.sendPayload(firstScrubPosition)
                .withEvent(Events.USER_SCRUB);

        bus.sendPayload(expectedScrubPosition)
                .withEvent(Events.USER_SCRUB);

        mediaPlayer.announceScrubbingComplete();

        assertThat(mediaPlayer.seekingTo(), is(expectedScrubPosition));
    }

    @Test
    public void whenScrubbingFinishedOnlyLastUnprocessedUserScrubEventProcessed() {

        TimeInMilliseconds firstScrubPosition = TimeInMilliseconds.fromLong(300);

        TimeInMilliseconds secondScrubPosition = TimeInMilliseconds.fromLong(300);

        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(2300);

        bus.sendPayload(firstScrubPosition)
                .withEvent(Events.USER_SCRUB);

        bus.sendPayload(secondScrubPosition)
                .withEvent(Events.USER_SCRUB);

        bus.sendPayload(expectedScrubPosition)
                .withEvent(Events.USER_SCRUB);

        mediaPlayer.announceScrubbingComplete();

        assertThat(mediaPlayer.seekingTo(), is(expectedScrubPosition));
    }
}