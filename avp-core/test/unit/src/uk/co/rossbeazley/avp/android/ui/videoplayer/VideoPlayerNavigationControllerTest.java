package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VideoPlayerNavigationControllerTest implements ScreenStack {

    private Class actualClass;

    @Test
    public void pushesVideoPlayerFragmentOnVideoLoadEvent() {
        //Events.USER_LOAD_VIDEO
        UriString anyUriString = new UriString("ANY");
        EventBus bus = new ExecutorEventBus();

        new VideoPlayerNavigationController(this, bus);

        bus.sendPayload(anyUriString).withEvent(Events.USER_LOAD_VIDEO);
        Class expectedClass = VideoControlScreen.class;
        assertThat(actualClass, is(equalTo(expectedClass)));

    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        this.actualClass = screenClass;
    }
}
