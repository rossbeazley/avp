package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public final class ScreenShowBufferingTest {

    private Integer bufferingIndicator;

    @Test
    public void buffering() {
        videoScreen.hideBuffering();
        videoScreen.showBuffering();
        bufferingIndicator = getViewById(R.id.buffering).getVisibility();
        assertThat(bufferingIndicator, is(View.VISIBLE));
    }


    @Test
    public void stopBuffering() {
        videoScreen.showBuffering();
        videoScreen.hideBuffering();
        bufferingIndicator = getViewById(R.id.buffering).getVisibility();
        assertThat(bufferingIndicator, is(not(View.VISIBLE)));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityForTestingViews.createVisibleActivityForLayout(R.layout.videoplayer);
        videoScreen = new VideoScreenAndroidView(activity.viewFinder());
    }


    private VideoPlayerScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
