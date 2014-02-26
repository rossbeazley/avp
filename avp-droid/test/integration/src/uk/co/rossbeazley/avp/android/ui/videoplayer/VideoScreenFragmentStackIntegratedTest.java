package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class VideoScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {

    @Override
    @Test
    public void testPushFragment() throws Exception {
        ScreenStack stack = createScreenFragmentStack();

        stack.pushScreen(VideoControlScreen.class);

        assertThat(fragmentAttached(), is((VideoPlayerFragment.class)));
    }

}
