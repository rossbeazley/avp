package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(manifest = Config.NONE)
public final class SearchScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {

    public void testPushFragment(ScreenStack screenFragmentStack) throws Exception {

        screenFragmentStack.pushScreen(SearchScreen.class);

        assertThat(fragmentAttached(), is((SearchFragment.class)));
    }

}
