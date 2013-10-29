package uk.co.rossbeazley.avp.android.player.time;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimePositionEventsTest {
    private MediaTimePosition timeInEvent;
    private MediaTimePosition expectedTime;
    private FakeMediaPlayer mediaPlayer;
    private EventBus bus;
    private TimeInMilliseconds totalLength;
    private int numberOfEvents;
    private FakeScheduledExecutor executor;

    @Before
    public void setup() {
        mediaPlayer = FakeMediaPlayer.createStartedFakeMediaPlayer();
        bus = new ExecutorEventBus();
        totalLength = mediaPlayer.getDuration();
        numberOfEvents = 0;
        executor = new FakeScheduledExecutor();
    }

    @Test
    public void theMediaPlayerTimeIsSentOnAnEvent() {
        TimeInMilliseconds currentPosition = new TimeInMilliseconds(1892);
        mediaPlayer.setCurrentPosition(currentPosition);

        expectedTime = new MediaTimePosition(currentPosition, totalLength);

        bus.whenEvent(Events.MEDIA_PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        timeInEvent = payload;
                    }
                });

        new MediaPlayerTimePositionWatcher(mediaPlayer, executor, bus); //TODO this object will need to repeatable watch the media player, we need something to execute that task for the watcher
        executor.runOnce();
        assertThat(timeInEvent, is(expectedTime));
    }


    @Test
    public void whenTheTimeChangesOnTheMediaPlayerAnEventIsRaised() {
        new MediaPlayerTimePositionWatcher(mediaPlayer, executor, bus);
        bus.whenEvent(Events.MEDIA_PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        timeInEvent = payload;
                    }
                });

        executor.runOnce();

        TimeInMilliseconds newPosition = new TimeInMilliseconds(2000);
        MediaTimePosition lExpectedTime = new MediaTimePosition(newPosition, totalLength);
        mediaPlayer.setCurrentPosition(newPosition);

        executor.runOnce();

        assertThat(timeInEvent, is(lExpectedTime));
    }

    @Test
    public void whenTheTimeDosntChangeNoEventIsGenerated() {
        TimeInMilliseconds currentPosition = new TimeInMilliseconds(1892);
        mediaPlayer.setCurrentPosition(currentPosition);

        bus.whenEvent(Events.MEDIA_PLAYER_TIME_UPDATE)
                .thenRun(new FunctionWithParameter<MediaTimePosition>() {
                    @Override
                    public void invoke(MediaTimePosition payload) {
                        numberOfEvents++;
                    }
                });

        new MediaPlayerTimePositionWatcher(mediaPlayer, executor, bus);
        executor.runOnce();
        executor.runOnce();
        assertThat(numberOfEvents, is(1));
    }

    public interface CanExecuteCommandsAtFixedRate {
        public void schedule(Runnable command, TimeInMilliseconds period);
        public void cancel();
    }

    private class FakeScheduledExecutor implements CanExecuteCommandsAtFixedRate{

        private Runnable taskToRun;

        @Override
        public void schedule(Runnable command, TimeInMilliseconds period) {
            taskToRun = command;
        }

        @Override
        public void cancel() { }

        public void runOnce() {
            if (taskToRun != null) {
                taskToRun.run();
            }
        }
    }
}
