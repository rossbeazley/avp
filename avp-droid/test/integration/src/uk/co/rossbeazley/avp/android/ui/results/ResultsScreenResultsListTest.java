package uk.co.rossbeazley.avp.android.ui.results;

import android.widget.ListAdapter;
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

    private ListAdapter adapter;
    private MediaItem firstMediaItem;
    private MediaItem secondMediaItem;

    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        firstMediaItem = new MediaItem("first");
        secondMediaItem = new MediaItem("second");
        Results results = new Results(firstMediaItem, secondMediaItem);

        ActivityForTestingViews visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.results);
        CanFindViewById findsViews;
        findsViews = visibleActivityForLayout.viewFinder();
        ResultsScreen screen = new ResultsScreenAndroid(findsViews);

        screen.showResults(results);
        ListView list = (ListView) findsViews.findViewById(R.id.searchresultslist);
        adapter = list.getAdapter();
    }

    @Test
    public void sizeReportedAsTwo() throws Exception {
        int listSize = adapter.getCount();
        assertThat(listSize, is(2));
    }


    @Test
    public void oneTypeOfView() {
        int numberOfTypes = adapter.getViewTypeCount();
        assertThat(numberOfTypes, is(1));
    }

    @Test
    public void isNotEmpty() {
        assertThat(adapter.isEmpty(),is(not(true)));
    }

    @Test
    public void idsAreStable() {
        assertThat(adapter.hasStableIds(),is(true));
    }

    @Test
    public void itemsAreAllEnabled() {
        assertThat(adapter.areAllItemsEnabled(),is(true));
    }

    @Test
    public void itemOneIsEnabled() {
        assertThat(adapter.isEnabled(1),is(true));
    }

    @Test
    public void itemTwoIsEnabled() {
        assertThat(adapter.isEnabled(2),is(true));
    }

    @Test
    public void returnsMediaItemOne() {
        assertThat(adapter.getItem(0), is((Object)firstMediaItem));
    }


    @Test
    public void returnsMediaItemTwo() {
        assertThat(adapter.getItem(1), is((Object)secondMediaItem));
    }

    /**
     *
     * assert behaviour of
     *
     *

     void registerDataSetObserver(android.database.DataSetObserver dataSetObserver);

     void unregisterDataSetObserver(android.database.DataSetObserver dataSetObserver);

     long getItemId(int i);

     android.view.View getView(int i, android.view.View view, android.view.ViewGroup viewGroup);

     int getItemViewType(int i);

     *
     *
     */


}
