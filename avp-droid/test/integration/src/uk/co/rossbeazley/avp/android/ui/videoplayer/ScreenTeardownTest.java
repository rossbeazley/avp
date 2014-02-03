package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.ui.Screen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenTeardownTest implements VideoControlScreen.CanListenForUserPlayEvents {

    @Test
    public void userPlayVideoEventWhenPlayClicked() {
        videoScreen.tearDown();

        assertThat("video stop", stopEvent,is(RAISED));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.videoplayer);
        VideoScreenViewRendererAndEventAdapter lvideoScreen = new VideoScreenViewRendererAndEventAdapter(activity.viewFinder());
        lvideoScreen.setPlayEventListener(this);
        videoScreen=lvideoScreen;
    }


    private Screen videoScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };
    private static final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };

    private Object stopEvent = NO_EVENT;

    public void userPressedPlay() {
        stopEvent = RAISED;
    }

    private void pressPlayButton() {
        clickOnID(R.id.play);
    }

    private void clickOnID(int id) {
        Robolectric.clickOn(activity.findViewById(id));
    }
}
