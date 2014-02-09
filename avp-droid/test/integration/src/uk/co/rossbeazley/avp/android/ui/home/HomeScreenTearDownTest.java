package uk.co.rossbeazley.avp.android.ui.home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenAndroidView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenTearDownTest implements  Screen.CanListenForScreenTearDownEvents {

    @Test
    public void tearDownWhenNoObserverScreen() {
        homeScreen.tearDown();
        assertThat("video stop", event,is(NO_EVENT));
    }

    @Test
    public void tearDownDispatchedFromScreen() {
        homeScreen.setTearDownEventListener(this);
        homeScreen.tearDown();
        assertThat("video stop", event,is(RAISED));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.home);
        HomeScreenAndroidView homeScreen = new HomeScreenAndroidView(activity.viewFinder());
        this.homeScreen =homeScreen;
    }


    private Screen homeScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };
    private static final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };

    private Object event = NO_EVENT;

    @Override
    public void screenTearDown() {
        event = RAISED;
    }
}
