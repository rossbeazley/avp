package uk.co.rossbeazley.avp.android.ui.search;

import android.view.View;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class SearchScreenTest implements SearchScreenAndroid.CanListenForUserSearchEvents {

    private boolean called = false;
    private String query_string = "any_old_query_string";
    private ActivityForTestingViews visibleActivityForLayout;
    private SearchScreen screen;

    @Before
    public void setUp() throws Exception {
        visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.search);
        screen = new SearchScreenAndroid(visibleActivityForLayout.viewFinder());
    }

    @Test
    public void clickingHomeButtonDispatchesEvent() {
        screen.setSearchEventListener(this);
        View goButton = visibleActivityForLayout.findViewById(R.id.go);
        Robolectric.clickOn(goButton);

        assertThat(called,is(true));
    }

    @Test
    public void returnsTextFromTheQueryStringTextBos() {
        TextView tv = (TextView) visibleActivityForLayout.findViewById(R.id.searchString);
        tv.setText(query_string);

        String text;
        text = screen.getQueryString();
        assertThat(text, is(query_string));
    }

    @Override
    public void userPressedSearch() {
        this.called=true;
    }
}
