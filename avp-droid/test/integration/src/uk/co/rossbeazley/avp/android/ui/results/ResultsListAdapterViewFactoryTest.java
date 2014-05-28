package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ResultsListAdapterViewFactoryTest {

    public static final int FOR_ITEM_ZERO = 0;
    private ListAdapter adapter;
    public static final View NO_VIEW_TO_RECYCLE = null;


    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        MediaItem mediaItem = new MediaItem("one");
        Results results = new Results(mediaItem);
        adapter = new ResultsListAdapter(results);
    }

    @Test
    public void viewRowCreated() {
        ActivityForTestingViews activity = ActivityForTestingViews.createVisibleActivity();
        ListView parentViewGroup = new ListView(activity);

        View createdView = adapter.getView(FOR_ITEM_ZERO, NO_VIEW_TO_RECYCLE, parentViewGroup);

        assertThat(createdView,is(notNullValue()));
    }

    @Test
    public void viewRowRecycled() {
        ActivityForTestingViews activity = ActivityForTestingViews.createVisibleActivity();
        ListView parentViewGroup = new ListView(activity);
        View viewToRecycle = new ResultsListItemView(activity);

        View createdView = adapter.getView(FOR_ITEM_ZERO, viewToRecycle, parentViewGroup);

        assertThat(createdView,is(notNullValue()));
        assertThat(createdView,is(viewToRecycle));
    }

}
