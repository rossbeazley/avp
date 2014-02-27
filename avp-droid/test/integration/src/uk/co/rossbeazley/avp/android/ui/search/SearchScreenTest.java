package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SearchScreenTest implements SearchScreenAndroid.CanListenForUserSearchEvents {

    private boolean called = false;

    @Test
    public void clickingHomeButtonDispatchesEvent() {

        ActivityForTestingViews act = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.search);
        SearchScreen screen = new SearchScreenAndroid(act.viewFinder());
        screen.setSearchEventListener(this);
        Robolectric.clickOn(act.findViewById(R.id.go));

        assertThat(called,is(true));
    }

    @Override
    public void userPressedSearch() {
        this.called=true;
    }
}
