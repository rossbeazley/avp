package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.MediaPlaybackService;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public final class VideoPlayerNavigationControllerTest implements ScreenStack {

    private Class actualClass;

    @Test
    public void pushesVideoPlayerFragmentOnVideoLoadEvent() {
        //Events.USER_LOAD_VIDEO
        UriString anyUriString = UriString.from("ANY");
        EventBus bus = new ExecutorEventBus();

        new VideoPlayerNavigationController(this, bus);

        bus.sendPayload(anyUriString).withEvent(MediaPlaybackService.USER_LOAD_VIDEO);
        Class expectedClass = MediaPlayerScreen.class;
        assertThat(actualClass, is(equalTo(expectedClass)));

    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        this.actualClass = screenClass;
    }
}
