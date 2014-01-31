package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.DefaultInflatedViewFactory;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedViewFactory;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class VideoPlayerFragmentInjectorTest implements InjectableVideoPlayerFragment {
    private FragmentScreenFactory fragmentScreenFactory;
    private InflatedViewFactory inflatedViewFactory;

    @Test
    public void testInject() throws Exception {
        VideoPlayerFragmentInjector videoPlayerFragmentInjector;
        videoPlayerFragmentInjector = new VideoPlayerFragmentInjector(new ExecutorEventBus());

        videoPlayerFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory,is(VideoPlayerFragmentScreenFactory.class));
        assertThat(inflatedViewFactory,is(DefaultInflatedViewFactory.class));
    }


    @Override
    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    @Override
    public void setInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }
}
