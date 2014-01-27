package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoControlScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenViewRendererAndEventAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenShowBufferingTest {

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
        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        videoScreen = new VideoScreenViewRendererAndEventAdapter(activity.viewFinder());
    }


    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
