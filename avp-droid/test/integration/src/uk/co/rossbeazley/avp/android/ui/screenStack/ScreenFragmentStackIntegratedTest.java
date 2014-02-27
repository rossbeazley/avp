package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public abstract class ScreenFragmentStackIntegratedTest implements FragmentTransaction {
    private Fragment fragmentAttached;

    @Test
    public abstract void testPushFragment() throws Exception;

    public ScreenStack createScreenFragmentStack() {
        FragmentFromScreen fragmentFromScreen = new DefaultFragmentFromScreen();
        FragmentTransaction fragmentTransaction = this;
        return new ScreenFragmentStack(fragmentFromScreen, fragmentTransaction);
    }

    @Override
    public void addFragmentToBackStack(Fragment fragment) {
        fragmentAttached = fragment;
    }

    public Fragment fragmentAttached() {
        return fragmentAttached;
    }
}
