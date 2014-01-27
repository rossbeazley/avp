package uk.co.rossbeazley.avp.android.ui.videoplayer;

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
public class ScreenPlayTest implements VideoControlScreen.CanListenForUserPlayEvents {

    @Test
    public void userPlayVideoEventWhenPlayClicked() {
        videoScreen.showPlay();
        pressPlayButton();
        assertThat("play event", playEvent,is(RAISED));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        VideoScreenViewRendererAndEventAdapter lvideoScreen = new VideoScreenViewRendererAndEventAdapter(activity.viewFinder());
        lvideoScreen.setPlayEventListener(this);
        videoScreen=lvideoScreen;
    }


    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private static final Object RAISED = new Object() { public String toString() { return "Event Raised";} };

    private final Object NO_EVENT = new Object() { public String toString() { return "no event"; } };
    private Object playEvent = NO_EVENT;

    public void userPressedPlay() {
        playEvent = RAISED;
    }

    private void pressPlayButton() {
        clickOnID(R.id.play);
    }

    private void clickOnID(int id) {
        Robolectric.clickOn(activity.findViewById(id));
    }
}
