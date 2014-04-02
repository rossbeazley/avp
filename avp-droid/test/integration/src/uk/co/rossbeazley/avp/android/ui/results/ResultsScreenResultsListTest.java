package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class ResultsScreenResultsListTest {
    @Test
    public void testShowResults() throws Exception {
        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        ResultsScreen screen = new ResultsScreenAndroid(visibleActivityForLayout.viewFinder());

        fail("NOT SPECED YET, when show results, the list adapter has the same number");
    }

}
