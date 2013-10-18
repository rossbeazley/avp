package uk.co.rossbeazley.avp.android.player.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.AndroidVideoScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.CanListenForUserPlayEvents;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenPlayTest implements CanListenForUserPlayEvents {



    @Test
    public void userPlayVideoEventWhenPlayClicked() {
        pressPlayButton();
        assertThat("play event", playEvent,is(RAISED));
    }


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ActivityForTestingViews.class).create().start().visible().get();
        AndroidVideoScreen lvideoScreen = new AndroidVideoScreen(activity.layoutInflater(), activity.viewFinder());
        lvideoScreen.setPlayEventListener(this);
        videoScreen=lvideoScreen;
        videoScreen.bind();
    }


    private VideoScreen videoScreen;

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
