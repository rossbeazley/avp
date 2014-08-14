package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class ResultsScreenSpinnerTest {
    @Test
    public void spinnerShowAsDefault() throws Exception {
        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        ResultsScreen screen = new ResultsScreenAndroid(visibleActivityForLayout.viewFinder());
        View spinnerView = visibleActivityForLayout.findViewById(R.id.searchspinner);
        assertThat(spinnerView.getVisibility(), is(View.VISIBLE));
    }

    @Test
    public void spinnerHides() throws Exception {
        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        ResultsScreen screen = new ResultsScreenAndroid(visibleActivityForLayout.viewFinder());

        screen.hideSpinner();

        View spinnerView = visibleActivityForLayout.findViewById(R.id.searchspinner);
        assertThat(spinnerView.getVisibility(), is(View.GONE));
    }

}
