package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.Screen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class VideoScreenTearDownTest implements  Screen.CanListenForScreenTearDownEvents {

    @Test
    public void tearDownDispatchedFromScreen() {
        videoScreen.tearDown();
        assertThat("video stop", event,is(RAISED));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.videoplayer);
        VideoScreenAndroidView lVideoScreen = new VideoScreenAndroidView(activity.viewFinder());
        lVideoScreen.setTearDownEventListener(this);
        videoScreen=lVideoScreen;
    }


    private Screen videoScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };
    private static final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };

    private Object event = NO_EVENT;

    @Override
    public void screenTearDown() {
        event = RAISED;
    }
}
