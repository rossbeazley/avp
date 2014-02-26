package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class FragmentManagerTransactionIntergrationTest {

    /** consider giving fragments tags so they can be asserted to exist in a test */

    @Test
    public void testPushFragment() throws Exception {
        ActivityForTestingViews activity = ActivityForTestingViews.createVisibleActivity();

        FragmentManager fragmentManager = activity.getFragmentManager();
        new FragmentManagerTransaction(fragmentManager).addFragmentToBackStack(new Fragment());
        assertThat(activity.lastFragmentAttached, is((Fragment.class)));
    }

}
