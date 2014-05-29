package uk.co.rossbeazley.avp.android.ui.results;

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
public class ResultsListAdapterGetViewTest {

    private ListAdapter adapter;
    public static final View NO_VIEW_TO_RECYCLE = null;
    private ActivityForTestingViews activityForTestingViews;
    private ListView parentViewGroup;


    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        MediaItem mediaItem = new MediaItem("one");
        Results results = new Results(mediaItem);
        adapter = new ResultsListAdapter(results);
        activityForTestingViews = ActivityForTestingViews.createVisibleActivity();
        parentViewGroup = new ListView(activityForTestingViews);
    }

    @Test
    public void viewRowCreated() {
        final int FOR_ITEM_ZERO = 0;
        View createdView = adapter.getView(FOR_ITEM_ZERO, NO_VIEW_TO_RECYCLE, parentViewGroup);

        assertThat(createdView,is(ResultsListItemView.class));
    }

    @Test
    public void viewRowRecycled() {
        View viewToRecycle = new ResultsListItemView(activityForTestingViews);
        final int FOR_ITEM_ZERO = 0;

        View createdView = adapter.getView(FOR_ITEM_ZERO, viewToRecycle, parentViewGroup);

        assertThat(createdView,is(viewToRecycle));
    }

    @Test
    public void viewContainsATextViewWithMediaItemText() {
        final int FOR_ITEM_ZERO = 0;
        View createdView = adapter.getView(FOR_ITEM_ZERO, NO_VIEW_TO_RECYCLE, parentViewGroup);

        ArrayList<View> foundViews = new ArrayList<View>();
        createdView.findViewsWithText(foundViews, "one", View.FIND_VIEWS_WITH_TEXT);

        assertThat( ((TextView)foundViews.get(0)).getText().toString(), is("one"));
    }

}
