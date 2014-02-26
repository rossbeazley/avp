package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.ScreenFragmentStackIntegratedTest;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(manifest = Config.NONE)
public class SearchScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {

    @Test
    public void testPushFragment() throws Exception {
        ScreenStack stack = createScreenFragmentStack();

        stack.pushScreen(SearchScreen.class);

        assertThat(fragmentAttached(), is((SearchFragment.class)));
    }

}
