package uk.co.rossbeazley.avp.android.ui.search;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.DefaultFragmentFromScreen;
import uk.co.rossbeazley.avp.android.ui.FragmentFromScreen;
import uk.co.rossbeazley.avp.android.ui.FragmentTransaction;
import uk.co.rossbeazley.avp.android.ui.ScreenFragmentStack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SearchScreenFragmentStackIntegratedTest implements FragmentTransaction {

    private Fragment fragmentAttached;

    @Test
    public void testPushFragment() throws Exception {
        ScreenFragmentStack stack = createScreenFragmentStack();

        stack.pushScreen(SearchScreenView.class);

        assertThat(fragmentAttached, is((SearchFragment.class)));
    }

    private ScreenFragmentStack createScreenFragmentStack() {
        FragmentFromScreen fragmentFromScreen = new DefaultFragmentFromScreen();
        FragmentTransaction fragmentTransaction = this;
        return new ScreenFragmentStack(fragmentFromScreen, fragmentTransaction);
    }

    @Override
    public void addFragmentToBackStack(Fragment fragment) {
        fragmentAttached = fragment;
    }

}
