package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoControlScreenMediatorScrubbingTest {

    private EventBus bus;
    private FakeVideoScreenVideo fakeVideoScreen;
    private TimeInMilliseconds scrubTime;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        fakeVideoScreen = new FakeVideoScreenVideo();
        VideoControlScreenMediator controller = new VideoControlScreenMediator(bus, fakeVideoScreen);

        MediaTimePosition TEN_SECOND_VIDEO = new MediaTimePosition(TimeInMilliseconds.fromInt(0), TimeInMilliseconds.fromInt(10000));
        bus.sendPayload(TEN_SECOND_VIDEO)
           .withEvent(Events.PLAYER_TIME_UPDATE);
    }

    @Test
    public void whenTimeUpdatedScrubBarMaxUpdated() {
        assertThat(fakeVideoScreen.scrubBarMax(),is(10000l));
    }

    @Test
    public void whenTimeUpdatedScrubBarPositionUpdated() {
        MediaTimePosition TEN_SECOND_VIDEO_POSITION_FOUR = new MediaTimePosition(TimeInMilliseconds.fromInt(4000), TimeInMilliseconds.fromInt(10000));
        bus.sendPayload(TEN_SECOND_VIDEO_POSITION_FOUR)
                .withEvent(Events.PLAYER_TIME_UPDATE);
        assertThat(fakeVideoScreen.scrubBarPosition(),is(4000l));
    }

    @Test
    public void whenUserScrubsToStartScrubEventWithZeroMillis() {
        bus.whenEvent(Events.USER_SCRUB)
                .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                    @Override
                    public void invoke(TimeInMilliseconds payload) {
                        scrubTime = payload;
                    }
                });
        TimeInMilliseconds expectedScrubTime = TimeInMilliseconds.fromLong(0);
        fakeVideoScreen.scrubTo(0);
        assertThat(scrubTime, is(expectedScrubTime));
    }


    @Test
    public void whenUserScrubsThirtyPercentScrubEventWith300Millis() {
        bus.whenEvent(Events.USER_SCRUB)
                .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                    @Override
                    public void invoke(TimeInMilliseconds payload) {
                        scrubTime = payload;
                    }
                });
        TimeInMilliseconds expectedScrubTime = TimeInMilliseconds.fromLong(3000);
        fakeVideoScreen.scrubTo(3000);
        assertThat(scrubTime, is(expectedScrubTime));
    }



    @Test
    public void whenUserScrubsToEndScrubEventWith1000Millis() {
        bus.whenEvent(Events.USER_SCRUB)
                .thenRun(new FunctionWithParameter<TimeInMilliseconds>() {
                    @Override
                    public void invoke(TimeInMilliseconds payload) {
                        scrubTime = payload;
                    }
                });
        TimeInMilliseconds expectedScrubTime = TimeInMilliseconds.fromLong(10000);
        fakeVideoScreen.scrubTo(10000);
        assertThat(scrubTime, is(expectedScrubTime));
    }


}
