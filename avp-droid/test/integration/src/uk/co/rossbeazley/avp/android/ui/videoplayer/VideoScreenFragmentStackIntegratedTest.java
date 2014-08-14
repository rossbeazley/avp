package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Ignore;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public final class VideoScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {

    @Override
    public void testPushFragment(ScreenStack screenFragmentStack) throws Exception {
        /*


        MID REFACTOR

        screenFragmentStack.pushScreen(MediaPlayerScreen.class);

        assertThat(fragmentAttached(), is((VideoPlayerFragment.class)));
        */
    }

}
