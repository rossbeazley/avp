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

    public static final int START = 0;
    public static final int THIRTY_PERCENT = 30;
    public static final int END = 100;
    private EventBus bus;
    private FakeVideoScreenVideo fakeVideoScreen;
    private boolean pausedEventDispatched;
    private boolean playEventDispatched;
    private TimeInMilliseconds scrubTime;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        fakeVideoScreen = new FakeVideoScreenVideo();
        VideoControlScreenMediator controller = new VideoControlScreenMediator(bus);
        controller.registerOnEventBus(fakeVideoScreen);

        MediaTimePosition TEN_SECOND_VIDEO = new MediaTimePosition(TimeInMilliseconds.fromInt(0), TimeInMilliseconds.fromInt(1000));
        bus.sendPayload(TEN_SECOND_VIDEO)
           .withEvent(Events.PLAYER_TIME_UPDATE);
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
        fakeVideoScreen.scrubTo(START);
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
        TimeInMilliseconds expectedScrubTime = TimeInMilliseconds.fromLong(300);
        fakeVideoScreen.scrubTo(THIRTY_PERCENT);
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
        TimeInMilliseconds expectedScrubTime = TimeInMilliseconds.fromLong(1000);
        fakeVideoScreen.scrubTo(END);
        assertThat(scrubTime, is(expectedScrubTime));
    }


}
