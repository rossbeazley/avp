package uk.co.rossbeazley.avp.android.ui.urlloader;

import org.junit.Test;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(manifest = Config.NONE)
public final class UrlLoaderScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {

    public void testPushFragment(ScreenStack screenFragmentStack) throws Exception {

        screenFragmentStack.pushScreen(UrlLoaderScreen.class);

        assertThat(fragmentAttached(), is((UrlLoaderFragment.class)));
    }

}
