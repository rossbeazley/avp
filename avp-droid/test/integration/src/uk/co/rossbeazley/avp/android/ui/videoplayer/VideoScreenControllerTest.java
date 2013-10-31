package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoScreenControllerTest {

    private EventBus bus;
    private FakeVideoScreen fakeVideoScreen;
    private boolean pausedEventDispatched;
    private boolean playEventDispatched;
    private TimeInMilliseconds scrubTime;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        fakeVideoScreen = new FakeVideoScreen();
        VideoScreenController controller = new VideoScreenController(bus);
        controller.registerOnEventBus(fakeVideoScreen);
    }

    @Test
    public void whenPlayingEventShowPauseHideBuffering() {
        bus.announce(Events.PLAYER_PLAYING);
        assertThat(fakeVideoScreen.showPause && fakeVideoScreen.hideBuffering, is(true));
    }

    @Test
    public void whenPausedEventShowPlayHideBuffering() {
        assertThat(fakeVideoScreen.showPlay,is(false));
        bus.announce(Events.PLAYER_PAUSED);
        assertThat(fakeVideoScreen.showPlay && fakeVideoScreen.hideBuffering, is(true));
    }

    @Test
    public void whenUserHitsPauseEventIsRaised() {
        bus.whenEvent(Events.PAUSE)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        pausedEventDispatched = true;

                    }
                });
        fakeVideoScreen.pausePressed();
        assertThat(pausedEventDispatched, is(true));
    }

    @Test
    public void whenUserHitsPlayEventIsRaised() {
        bus.whenEvent(Events.PLAY)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        playEventDispatched = true;

                    }
                });
        fakeVideoScreen.playPressed();
        assertThat(playEventDispatched, is(true));
    }

    @Test
    public void whenUserScrubsScrubEventWithPayloadRaised() {
        bus.whenEvent(Events.SCRUB)
                .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                    @Override
                    public void invoke(TimeInMilliseconds payload) {
                        scrubTime = payload;
                    }
                });
        TimeInMilliseconds expectedScrubTime = new TimeInMilliseconds(123456);
        fakeVideoScreen.scrubTo(expectedScrubTime);
        assertThat(scrubTime, is(expectedScrubTime));
    }

    @Test
    public void whenTimeUpdateEventCurrentTimeUpdatedOnScreen() {

        TimeInMilliseconds expectedTime = new TimeInMilliseconds(1000);
        TimeInMilliseconds ANY_TIME = new TimeInMilliseconds(0);
        MediaTimePosition mediaPlayerTimePosition = new MediaTimePosition(expectedTime, ANY_TIME);
        bus.sendPayload(mediaPlayerTimePosition).withEvent(Events.MEDIA_PLAYER_TIME_UPDATE);

        assertThat(fakeVideoScreen.progressTime, is(expectedTime));
    }

    @Test
    public void whenTimeUpdateEventDurationTimeUpdatedOnScreen() {

        TimeInMilliseconds expectedTime = new TimeInMilliseconds(1000);
        TimeInMilliseconds ANY_TIME = new TimeInMilliseconds(0);
        MediaTimePosition mediaPlayerTimePosition = new MediaTimePosition(ANY_TIME, expectedTime);
        bus.sendPayload(mediaPlayerTimePosition).withEvent(Events.MEDIA_PLAYER_TIME_UPDATE);

        assertThat(fakeVideoScreen.totalTime, is(expectedTime));
    }

}