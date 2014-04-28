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
public class ResultsListAdapterViewFactoryTest {

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
    public void viewCreatedWithATextView() {

    }

    @Test
    public void viewRecycled() {

    }

}
