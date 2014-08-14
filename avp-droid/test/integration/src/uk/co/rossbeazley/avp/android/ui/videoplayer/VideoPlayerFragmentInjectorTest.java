package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public final class VideoPlayerFragmentInjectorTest implements InjectableVideoPlayerFragment {
    private FragmentScreenFactory fragmentScreenFactory;

    @Test
    public void testInject() throws Exception {
        VideoPlayerFragmentInjector videoPlayerFragmentInjector;
        videoPlayerFragmentInjector = new VideoPlayerFragmentInjector(new ExecutorEventBus());

        videoPlayerFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory,is(VideoPlayerFragmentScreenFactory.class));
    }


    @Override
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

}
