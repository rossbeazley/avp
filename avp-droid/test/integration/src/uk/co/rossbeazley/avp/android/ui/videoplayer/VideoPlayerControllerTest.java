package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class VideoPlayerControllerTest implements FragmentStack {

    private Fragment fragment;

    @Test
    public void pushesVideoPlayerFragmentOnVideoLoadEvent() {
        //Events.USER_LOAD_VIDEO
        UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
        EventBus bus = new ExecutorEventBus();
        bus.sendPayload(uriString).withEvent(Events.USER_LOAD_VIDEO);

        new VideoPlayerController(this, bus);

        assertThat(fragment.getClass(), is(VideoPlayerFragment.class));

    }

    @Override
    public void pushFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
