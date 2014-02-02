package uk.co.rossbeazley.avp.android.ui.home;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenViewTest implements HomeScreenAndroidView.CanListenForUserSearchEvents {

    private boolean called = false;

    @Test
    public void clickingHomeButtonDispatchesEvent() {

        ActivityForTestingViews act = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.home);
        HomeScreenView screen = new HomeScreenAndroidView(act.viewFinder());
        screen.setSearchEventListener(this);
        Robolectric.clickOn(act.findViewById(R.id.go));

        assertThat(called,is(true));
    }

    @Override
    public void userPressedSearch() {
        this.called=true;
    }
}
