package uk.co.rossbeazley.redux.android.videoplayer.ui;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.redux.android.ActivityForTestingViews;
import uk.co.rossbeazley.redux.android.R;
import uk.co.rossbeazley.redux.android.ui.videoplayer.AndroidVideoScreen;
import uk.co.rossbeazley.redux.android.ui.videoplayer.VideoScreen;

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
        playButton = getViewById(R.id.play).getVisibility();
        pauseButton = getViewById(R.id.pause).getVisibility();
        assertThat(playButton, is(View.VISIBLE));
        assertThat(pauseButton, is(not(View.VISIBLE)));
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
        activity = new ActivityForTestingViews();
        videoScreen = new AndroidVideoScreen(activity.layoutInflater(), activity.viewFinder());
        videoScreen.bind();
    }


    private VideoScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
