package uk.co.rossbeazley.avp.android.ui.urlloader;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderScreenAndroid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UrlLoaderScreenGotoSearchTest implements UrlLoaderScreen.CanListenForUserGotoSearchScreenEvents {

    private boolean called = false;
    private ActivityForTestingViews visibleActivityForLayout;
    private UrlLoaderScreen screen;

    @Before
    public void setUp() throws Exception {
        visibleActivityForLayout = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.urlloader);//TODO change this to real layout
        screen = new UrlLoaderScreenAndroid(visibleActivityForLayout.viewFinder());
    }

    @Test
    public void clickingGoButtonDispatchesEvent() {
        screen.setGotoSearchEventListener(this);
        View button = visibleActivityForLayout.findViewById(R.id.search);
        Robolectric.clickOn(button);

        assertThat(called,is(true));
    }

    @Override
    public void userPressedGotoSearch() {
        this.called=true;
    }
}
