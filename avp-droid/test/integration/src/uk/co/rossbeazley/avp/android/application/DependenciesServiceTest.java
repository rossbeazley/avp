package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static junit.framework.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class DependenciesServiceTest {

    @Test
    public void testInjectDependencies() throws Exception {
        DependenciesService ds = new DependenciesService(new ExecutorEventBus());
        VideoPlayerFragment frag = new VideoPlayerFragment();
        ds.injectDependencies(frag);

        fail("Finish this test and pull out injector map");
    }

}



