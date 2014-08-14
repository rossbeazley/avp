package uk.co.rossbeazley.avp.android.ui.results;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class ResultsListAdapterGetViewTest {

    public static final String PROGRAM_TITLE = "media item ";
    private ListAdapter adapter;
    public static final View NO_VIEW_TO_RECYCLE = null;
    private ActivityForTestingViews activityForTestingViews;
    private ListView parentViewGroup;
    public static final int ITEM_ZERO = 0;
    public static final int ITEM_ONE = 1;


    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        Results results = new Results(new MediaItem(PROGRAM_TITLE+ITEM_ZERO), new MediaItem(PROGRAM_TITLE+ITEM_ONE));

        adapter = new ResultsListAdapter(results);
        activityForTestingViews = ActivityForTestingViews.createVisibleActivity();
        parentViewGroup = new ListView(activityForTestingViews);
    }

    @Test
    public void viewRowCreated() {
        View createdView = adapter.getView(ITEM_ZERO, NO_VIEW_TO_RECYCLE, parentViewGroup);

        assertThat(createdView,is(ResultsListItemView.class));
    }

    @Test
    public void viewRowRecycled() {
        View viewToRecycle = LayoutInflater.from(Robolectric.application).inflate(R.layout.results_list_row, null);

        View createdView = adapter.getView(ITEM_ZERO, viewToRecycle, parentViewGroup);

        assertThat(createdView,is(viewToRecycle));
    }

    @Test
    public void firstItemInTheList() {
        View createdView = adapter.getView(ITEM_ZERO, NO_VIEW_TO_RECYCLE, parentViewGroup);

        ArrayList<View> foundViews = new ArrayList<View>();
        createdView.findViewsWithText(foundViews, PROGRAM_TITLE, View.FIND_VIEWS_WITH_TEXT);

        assertThat( ((TextView)foundViews.get(0)).getText().toString(), is(PROGRAM_TITLE+ITEM_ZERO));
    }

    @Test
    public void secondItemInTheList() {
        View createdView = adapter.getView(ITEM_ONE, NO_VIEW_TO_RECYCLE, parentViewGroup);

        ArrayList<View> foundViews = new ArrayList<View>();
        createdView.findViewsWithText(foundViews, PROGRAM_TITLE, View.FIND_VIEWS_WITH_TEXT);

        assertThat( ((TextView)foundViews.get(0)).getText().toString(), is(PROGRAM_TITLE+ITEM_ONE));
    }

}
