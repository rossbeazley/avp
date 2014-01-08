package uk.co.rossbeazley.avp.android.player.time;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.FakePlaybackOfMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimePositionEventsTest {
    private MediaTimePosition timeInEvent;
    private MediaTimePosition expectedTime;
    private FakePlaybackOfMediaPlayer mediaPlayer;
    private EventBus bus;
    private TimeInMilliseconds totalLength;
    private int numberOfEvents;
    private FakeScheduledExecutor executor;

    @Before
    public void setup() {
        mediaPlayer = FakePlaybackOfMediaPlayer.createStartedFakeMediaPlayer();
        bus = new ExecutorEventBus();
        totalLength = mediaPlayer.getDuration();
        numberOfEvents = 0;
        executor = new FakeScheduledExecutor();
        new MediaPlayerTimePositionWatcher(executor, bus);

    }

    @Test
    public void theMediaPlayerTimeIsSentOnAnEvent() {
        TimeInMilliseconds currentPosition = TimeInMilliseconds.fromLong(1892);
        mediaPlayer.setCurrentPosition(currentPosition);

        expectedTime = new MediaTimePosition(currentPosition, totalLength);

        bus.whenEvent(Events.PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        timeInEvent = payload;
                    }
                });
        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);
        executor.runOnce();
        assertThat(timeInEvent, is(expectedTime));
    }


    @Test
    public void whenTheTimeChangesOnTheMediaPlayerAnEventIsRaised() {
        bus.whenEvent(Events.PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        timeInEvent = payload;
                    }
                });

        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);

        executor.runOnce();

        TimeInMilliseconds newPosition = TimeInMilliseconds.fromLong(2000);
        mediaPlayer.setCurrentPosition(newPosition);

        executor.runOnce();

        MediaTimePosition lExpectedTime = new MediaTimePosition(newPosition, totalLength);
        assertThat(timeInEvent, is(lExpectedTime));
    }

    @Test
    public void whenTheTimeDosntChangeNoEventIsGenerated() {
        TimeInMilliseconds currentPosition = TimeInMilliseconds.fromLong(1892);
        mediaPlayer.setCurrentPosition(currentPosition);

        bus.whenEvent(Events.PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        numberOfEvents++;
                    }
                });

        bus.sendPayload(mediaPlayer).withEvent(Events.PLAYER_VIDEO_LOADED);
        executor.runOnce();
        assertThat(numberOfEvents, is(1));

        executor.runOnce();
        assertThat(numberOfEvents, is(still(1)));
    }



    private Matcher<Integer> still(int i) {
        return is(i);
    }

}
