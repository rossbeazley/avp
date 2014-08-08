package uk.co.rossbeazley.avp.android.ui.results;

import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.screenStack.ScreenFragmentStackIntegratedTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Config(manifest = Config.NONE)
public class ResultsScreenFragmentStackIntegratedTest extends ScreenFragmentStackIntegratedTest {
    @Override
    public void testPushFragment(ScreenStack screenFragmentStack) throws Exception {

        screenFragmentStack.pushScreen(ResultsScreen.class);

        assertThat(fragmentAttached(), is((ResultsFragment.class)));

    }

}
