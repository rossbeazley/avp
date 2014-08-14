package uk.co.rossbeazley.avp.android.ui.playout;

import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;
import uk.co.rossbeazley.avp.android.ui.videoplayer.MediaPlayerScreen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class PlayoutScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest{
    @Override
    public void testPushFragment(ScreenStack screenFragmentStack) throws Exception {
        screenFragmentStack.pushScreen(MediaPlayerScreen.class);
        assertThat(fragmentAttached(), is((PlayoutFragment.class)));
    }
}
