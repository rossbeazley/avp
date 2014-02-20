package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class VideoPlayerNavigationControllerTest implements ScreenStack {

    private Class actualClass;

    @Test
    public void pushesVideoPlayerFragmentOnVideoLoadEvent() {
        //Events.USER_LOAD_VIDEO
        UriString anyUriString = new UriString("ANY");
        EventBus bus = new ExecutorEventBus();

        new VideoPlayerNavigationController(this, bus);

        bus.sendPayload(anyUriString).withEvent(Events.USER_LOAD_VIDEO);
        Class expectedClass = VideoPlayerFragment.class;
        assertThat(actualClass, is(equalTo(expectedClass)));

    }

    public void push(Class<? extends Fragment> fragmentClass) {
        this.actualClass = fragmentClass;
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
