package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.videoplayer.ActivityTestSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class FragmentManagerFragmentStackTest {
    @Test
    public void testPushFragment() throws Exception {
        ActivityForTestingViews activity = ActivityTestSupport.createVisibleActivity();

        FragmentManagerFragmentStack stack = new FragmentManagerFragmentStack(activity.getFragmentManager());
        stack.pushFragment(Fragment.class);

        assertThat(activity.lastFragmentAttached, is((Fragment.class)));
    }
}
