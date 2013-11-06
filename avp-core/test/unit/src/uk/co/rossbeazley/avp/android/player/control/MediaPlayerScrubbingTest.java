package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.mediaplayer.CanScrubMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
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
        new MediaPlayerScrubber(bus);
        bus.sendPayload(mediaPlayer)
                .withEvent(Events.PLAYER_VIDEO_LOADED);
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
        TimeInMilliseconds expectedScrubPosition = TimeInMilliseconds.fromLong(5000);

        TimeInMilliseconds secondScrubPosition = TimeInMilliseconds.fromLong(15000);

        bus.sendPayload(expectedScrubPosition)
                .withEvent(Events.USER_SCRUB);

        bus.sendPayload(secondScrubPosition)
                .withEvent(Events.USER_SCRUB);

        assertThat(mediaPlayer.seekingTo(),is(expectedScrubPosition));
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

        assertThat(mediaPlayer.seekingTo(),is(expectedScrubPosition));
    }

    @Test
    public void whenScrubbingFinishedOnlyLastUnprocessedUserScrubEventProcessed() {
        fail("not speced yet");
    }



    private class MediaPlayerScrubber {


        CanScrubMediaPlayer mediaPlayer;
        CanScrubMediaPlayer realMediaPlayer;
        final CanScrubMediaPlayer nullMediaPlayer = new CanScrubMediaPlayer() {
            @Override
            public void seekTo(TimeInMilliseconds time) {}

            @Override
            public void addScrubCompleteListener(ScrubCompleteListener listener) {
            }
        };

        public MediaPlayerScrubber(EventBus bus) {
            bindVideoLoadedEvent(bus);
            bindScrubEvent(bus);
        }

        private void bindScrubEvent(EventBus bus) {
            bus.whenEvent(Events.USER_SCRUB)
                    .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                        @Override
                        public void invoke(TimeInMilliseconds payload) {
                            mediaPlayer.seekTo(payload);
                            mediaPlayer = nullMediaPlayer;
                        }
                    });
        }

        private void bindVideoLoadedEvent(EventBus bus) {
            bus.whenEvent(Events.PLAYER_VIDEO_LOADED)
                    .thenRun(new FunctionWithParameter<CanScrubMediaPlayer>() {
                        @Override
                        public void invoke(CanScrubMediaPlayer payload) {
                            mediaPlayer = payload;
                            realMediaPlayer = payload;
                        }
                    });
        }
    }
}
