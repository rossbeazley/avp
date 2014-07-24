package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.state.MediaPlayerStateMachine;
import uk.co.rossbeazley.avp.android.player.time.MediaTimePosition;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoPlayerScreenPresenterTest {

    private EventBus bus;
    private FakeVideoScreenVideo fakeVideoScreen;
    private boolean pausedEventDispatched;
    private boolean playEventDispatched;
    private TimeInMilliseconds scrubTime;
    private boolean closeEventDispatched;
    private boolean exitEventDispatched;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        fakeVideoScreen = new FakeVideoScreenVideo();
        VideoPlayerScreenPresenter presenter = new VideoPlayerScreenPresenter(bus, fakeVideoScreen);
    }

    @Test
    public void whenPlayingEventShowPauseHideBuffering() {
        bus.announce(MediaPlayerStateMachine.PLAYER_PLAYING);
        assertThat(fakeVideoScreen.showPause && fakeVideoScreen.hideBuffering, is(true));
    }

    @Test
    public void whenPausedEventShowPlayHideBuffering() {
        assertThat(fakeVideoScreen.showPlay, is(false));
        bus.announce(MediaPlayerStateMachine.PLAYER_PAUSED);
        assertThat(fakeVideoScreen.showPlay && fakeVideoScreen.hideBuffering, is(true));
    }

    @Test
    public void whenUserHitsPauseEventIsRaised() {
        bus.whenEvent(VideoPlayerScreenPresenter.USER_PAUSE)
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
        bus.whenEvent(VideoPlayerScreenPresenter.USER_PLAY)
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
    public void whenTimeUpdateEventCurrentTimeUpdatedOnScreen() {

        TimeInMilliseconds expectedTime = TimeInMilliseconds.fromLong(1000);
        TimeInMilliseconds ANY_TIME = TimeInMilliseconds.fromLong(0);
        MediaTimePosition mediaPlayerTimePosition = new MediaTimePosition(expectedTime, ANY_TIME);
        bus.sendPayload(mediaPlayerTimePosition).withEvent(Events.PLAYER_TIME_UPDATE);

        assertThat(fakeVideoScreen.progressTime, is(expectedTime));
    }

    @Test
    public void whenTimeUpdateEventDurationTimeUpdatedOnScreen() {

        TimeInMilliseconds expectedTime = TimeInMilliseconds.fromLong(1000);
        TimeInMilliseconds ANY_TIME = TimeInMilliseconds.fromLong(0);
        MediaTimePosition mediaPlayerTimePosition = new MediaTimePosition(ANY_TIME, expectedTime);
        bus.sendPayload(mediaPlayerTimePosition).withEvent(Events.PLAYER_TIME_UPDATE);

        assertThat(fakeVideoScreen.totalTime, is(expectedTime));
    }

    @Test
    public void dispatchScreenExitEventWhenScreenTearDown() {
        bus.whenEvent(Events.USER_EXIT_VIDEO_SCREEN)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        exitEventDispatched = true;

                    }
                });
        fakeVideoScreen.tearDown();
        assertThat(exitEventDispatched, is(true));

    }
}
