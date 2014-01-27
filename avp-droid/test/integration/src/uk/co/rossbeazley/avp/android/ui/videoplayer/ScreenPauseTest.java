package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoControlScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenViewRendererAndEventAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenPauseTest implements VideoControlScreen.CanListenForUserPauseEvents {

    @Test
    public void userPauseVideoEventWhenPauseClicked() {
        videoScreen.showPause();
        pressPauseButton();
        assertThat("pause event", pauseEvent,is(RAISED));
    }

    @Override
    public void userPressedPause() {
        pauseEvent = RAISED;
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        VideoScreenViewRendererAndEventAdapter lvideoScreen = new VideoScreenViewRendererAndEventAdapter(activity.viewFinder());
        lvideoScreen.setPauseEventListener((VideoControlScreen.CanListenForUserPauseEvents)this);
        videoScreen = lvideoScreen;
    }

    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };

    private final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };
    private Object pauseEvent = NO_EVENT;

    private void pressPauseButton() {
        clickOnID(R.id.pause);
    }

    private void clickOnID(int id) {
        View viewToClick = activity.findViewById(id);
        Robolectric.clickOn(viewToClick);
    }
}
