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
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ResultsListAdapterViewFactoryTest {

    private ListAdapter adapter;

    @Before
    public void givenTwoResultAreShowOnTheScreen() {
        MediaItem mediaItem = new MediaItem("one");
        Results results = new Results(mediaItem);
        adapter = new ResultsListAdapter(results);
    }

    @Test
    public void viewCreatedWithATextView() {
        ViewGroup parentGroup = Robolectric.newInstanceOf(LinearLayout.class);
        View createdView = adapter.getView(0, null, parentGroup);
        assertThat(createdView,is(notNullValue()));
    }

    @Test
    public void viewRecycled() {
        ViewGroup parentGroup = Robolectric.newInstanceOf(LinearLayout.class);
        View createdView = adapter.getView(0, null, parentGroup);

        View recycledView = adapter.getView(0, createdView, parentGroup);
        assertThat(createdView,is(notNullValue()));
        assertThat(createdView,is(recycledView));
    }

}
