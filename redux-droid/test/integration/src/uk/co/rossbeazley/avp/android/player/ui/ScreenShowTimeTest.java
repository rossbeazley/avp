package uk.co.rossbeazley.avp.android.player.ui;

import android.view.View;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.AndroidVideoScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenShowTimeTest {


    private static final CharSequence THREE_SECONDS_IN_MILLIS = "3000";

    @Test
    public void totalTimeUpdatedOnScreen() {
        videoScreen.showTotalTime(new TimeInMilliseconds(3000));
        CharSequence textFromView = ((TextView) getViewById(R.id.totaltime)).getText();
        assertThat(textFromView, is(THREE_SECONDS_IN_MILLIS));
    }


    @Test
    public void progressTimeUpdatedOnScreen() {
        videoScreen.showProgressTime(new TimeInMilliseconds(3000));
        CharSequence textFromView = ((TextView) getViewById(R.id.currenttime)).getText();
        assertThat(textFromView, is(THREE_SECONDS_IN_MILLIS));
    }


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ActivityForTestingViews.class).create().start().visible().get();
        videoScreen = new AndroidVideoScreen(activity.layoutInflater(), activity.viewFinder());
        videoScreen.bind();
    }


    private VideoScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
