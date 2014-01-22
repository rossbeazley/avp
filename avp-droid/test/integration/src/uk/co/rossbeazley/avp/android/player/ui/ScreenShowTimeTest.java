package uk.co.rossbeazley.avp.android.player.ui;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoControlScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreenViewRenderer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenShowTimeTest {


    private static final CharSequence THREE_SECONDS_ZERO_MINS = "00:03";
    private int seekBarPosition;

    @Test
    public void totalTimeUpdatedOnScreen() {
        videoScreen.showTotalTime(TimeInMilliseconds.fromLong(3000));
        CharSequence textFromView = ((TextView) getViewById(R.id.totaltime)).getText();
        assertThat(textFromView, is(THREE_SECONDS_ZERO_MINS));
    }


    @Test
    public void progressTimeUpdatedOnScreen() {
        videoScreen.showProgressTime(TimeInMilliseconds.fromLong(3000));
        CharSequence textFromView = ((TextView) getViewById(R.id.currenttime)).getText();
        assertThat(textFromView, is(THREE_SECONDS_ZERO_MINS));
    }


    @Test
    public void progressTimeUpdatedOnSeekBar() {
        SeekBar seekbar = (SeekBar) getViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarPosition = i;
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        videoScreen.showSeekBarPosition(40, 1000);
        assertThat(seekBarPosition, is(40));
    }


    @Test
    public void maxTimeUpdatedOnSeekBar() {
        SeekBar seekbar = (SeekBar) getViewById(R.id.seekBar);
        videoScreen.showSeekBarPosition(40, 1000);
        assertThat(seekbar.getMax(), is(1000));
    }


    @Before
    public void setUp() throws Exception {
        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        videoScreen = new VideoScreenViewRenderer(activity.viewFinder());
    }


    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private View getViewById(int id) {
        return activity.findViewById(id);
    }
}
