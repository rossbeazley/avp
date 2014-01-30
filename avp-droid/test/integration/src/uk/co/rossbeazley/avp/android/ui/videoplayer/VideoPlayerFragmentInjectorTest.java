package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 30/01/2014
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
public class VideoPlayerFragmentInjectorTest implements InjectableVideoPlayerFragment {
    private FragmentScreenFactory fragmentScreenFactory;

    @Test
    public void testInject() throws Exception {
        VideoPlayerFragmentInjector videoPlayerFragmentInjector;
        videoPlayerFragmentInjector = new VideoPlayerFragmentInjector(new ExecutorEventBus());

        videoPlayerFragmentInjector.inject(this);

        assertThat(fragmentScreenFactory,is(VideoPlayerFragmentScreenFactory.class));
    }


    @Override
    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }
}
