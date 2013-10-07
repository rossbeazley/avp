package uk.co.rossbeazley.redux.android.videoplayer.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.redux.android.ActivityForTestingViews;
import uk.co.rossbeazley.redux.android.R;
import uk.co.rossbeazley.redux.android.ui.videoplayer.AndroidVideoScreen;
import uk.co.rossbeazley.redux.android.ui.videoplayer.CanListenForUserPauseEvents;
import uk.co.rossbeazley.redux.android.ui.videoplayer.VideoScreen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenPauseTest implements CanListenForUserPauseEvents {

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
        activity = new ActivityForTestingViews();
        AndroidVideoScreen lvideoScreen = new AndroidVideoScreen(activity.layoutInflater(), activity.viewFinder());
        lvideoScreen.setPauseEventListener((CanListenForUserPauseEvents)this);
        videoScreen = lvideoScreen;
        videoScreen.bind();
    }

    private VideoScreen videoScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };

    private final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };
    private Object pauseEvent = NO_EVENT;

    private void pressPauseButton() {
        clickOnID(R.id.pause);
    }

    private void clickOnID(int id) {
        Robolectric.clickOn(activity.findViewById(id));
    }
}
