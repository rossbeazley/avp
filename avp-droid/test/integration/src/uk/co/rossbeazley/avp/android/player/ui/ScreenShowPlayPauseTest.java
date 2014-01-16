package uk.co.rossbeazley.avp.android.player.ui;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoControlScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenViewRenderer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenShowPlayPauseTest {

    private Integer playButton;
    private int pauseButton;

    @Test
    public void showPlay() {
        videoScreen.showPlay();
        View viewById = getViewById(R.id.play);
        playButton = viewById.getVisibility();
        pauseButton = getViewById(R.id.pause).getVisibility();
        assertThat(playButton, is(View.VISIBLE));
        assertThat(pauseButton, is(not(View.VISIBLE)));
        Robolectric.clickOn(viewById);
    }


    @Test
    public void showPause() {
        videoScreen.showPause();
        playButton = getViewById(R.id.play).getVisibility();
        pauseButton = getViewById(R.id.pause).getVisibility();
        assertThat("play button visibility", playButton, is(not(View.VISIBLE)));
        assertThat("pause button visibility", pauseButton, is(View.VISIBLE));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityTestSupport.createVisibleActivity();
        videoScreen = new VideoScreenViewRenderer(activity.layoutInflater(), activity.viewFinder());
        videoScreen.bind();
    }


    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
