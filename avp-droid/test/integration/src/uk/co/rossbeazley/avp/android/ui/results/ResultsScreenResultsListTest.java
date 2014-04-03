package uk.co.rossbeazley.avp.android.ui.results;

import android.widget.ListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.media.Programme;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ResultsScreenResultsListTest {

    private CanFindViewById findsViews;

    @Before
    public void givenOneResultIsShowOnTheScreen() {
        Results results = new Results(new Programme(""));

        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        findsViews = visibleActivityForLayout.viewFinder();
        ResultsScreen screen = new ResultsScreenAndroid(findsViews);

        screen.showResults(results);
    }

    @Test
    public void testShowResults() throws Exception {

        int listSize;

        ListView list = (ListView) findsViews.findViewById(R.id.searchresultslist);

        listSize = list.getAdapter().getCount();

        assertThat(listSize, is(1));
    }

}
