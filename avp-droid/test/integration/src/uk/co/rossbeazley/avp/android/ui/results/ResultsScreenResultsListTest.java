package uk.co.rossbeazley.avp.android.ui.results;

import android.widget.ListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ResultsScreenResultsListTest {

    private ListView list;

    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        Results results = new Results(new MediaItem("first"),new MediaItem("second"));

        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        CanFindViewById findsViews;
        findsViews = visibleActivityForLayout.viewFinder();
        ResultsScreen screen = new ResultsScreenAndroid(findsViews);

        screen.showResults(results);
        list = (ListView) findsViews.findViewById(R.id.searchresultslist);
    }

    @Test
    public void sizeReportedAsTwo() throws Exception {
        int listSize = list.getAdapter().getCount();
        assertThat(listSize, is(2));
    }


    @Test
    public void oneTypeOfView() {
        int numberOfTypes = list.getAdapter().getViewTypeCount();
        assertThat(numberOfTypes, is(1));
    }

    @Test
    public void isNotEmpty() {
        assertThat(list.getAdapter().isEmpty(),is(not(true)));
    }

    @Test
    public void idsAreStable() {
        assertThat(list.getAdapter().hasStableIds(),is(true));
    }

    @Test
    public void itemsAreAllEnabled() {
        assertThat(list.getAdapter().areAllItemsEnabled(),is(true));
    }

    /**
     *
     * assert behaviour of
     *
     *

     boolean isEnabled(int i);


     void registerDataSetObserver(android.database.DataSetObserver dataSetObserver);

     void unregisterDataSetObserver(android.database.DataSetObserver dataSetObserver);

     java.lang.Object getItem(int i);

     long getItemId(int i);

     android.view.View getView(int i, android.view.View view, android.view.ViewGroup viewGroup);

     int getItemViewType(int i);

     *
     *
     */


}
