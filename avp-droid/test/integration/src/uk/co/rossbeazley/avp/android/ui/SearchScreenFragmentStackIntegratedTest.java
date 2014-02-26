package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchScreenView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SearchScreenFragmentStackIntegratedTest implements FragmentTransaction {

    private Fragment fragmentAttached;

    @Test
    public void testPushFragment() throws Exception {
        ScreenFragmentStack stack = new ScreenFragmentStack(createScreenToFragment(), this);
        stack.pushScreen(SearchScreenView.class);

        assertThat(fragmentAttached, is((SearchFragment.class)));
    }

    @Override
    public void addFragmentToBackStack(Fragment fragment) {
        fragmentAttached = fragment;
    }

    private FragmentFromScreen createScreenToFragment() {
        return new DefaultFragmentFromScreen();
    }

}
