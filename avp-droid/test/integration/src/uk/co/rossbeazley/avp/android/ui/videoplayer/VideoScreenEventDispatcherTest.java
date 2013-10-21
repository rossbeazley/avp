package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoScreenEventDispatcherTest {

    private boolean showPause;
    private boolean hideBuffering;

    @Test
    public void whenPlayingEventShowPauseHideBuffering() {
        EventBus bus = new ExecutorEventBus();
        VideoScreenEventDispatcher dispatcher = new VideoScreenEventDispatcher(bus);
        VideoScreen fakeVideoScreen = createFakeVideoScreen();
        dispatcher.registerOnEventBus(fakeVideoScreen);

        bus.announce(Events.PLAYER_PLAYING);

        assertThat(showPause && hideBuffering, is(true));
    }

    private VideoScreen createFakeVideoScreen() {
        return new VideoScreen() {
            @Override
            public void bind() {}

            @Override
            public void setPauseEventListener(CanListenForUserPauseEvents canListenForUserPauseEvents) {}

            @Override
            public void setPlayEventListener(CanListenForUserPlayEvents canListenForUserPlayEvents) {}

            @Override
            public void setScrubEventListener(CanListenForUserScrubEvents canListenForUserScrubEvents) {}

            @Override
            public void showTotalTime(TimeInMilliseconds time) {}

            @Override
            public void showProgressTime(TimeInMilliseconds time) {}

            @Override
            public void showBuffering() {}

            @Override
            public void hideBuffering() {
                hideBuffering = true;
            }

            @Override
            public void showPlay() {}

            @Override
            public void showPause() {
                showPause = true;
            }

            @Override
            public void attachVideo(RenderedVideoOutput videoOutput) {}
        };
    }
}
