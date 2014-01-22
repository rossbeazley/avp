package uk.co.rossbeazley.avp.android.player.ui;

import android.widget.SeekBar;
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
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenScrubTest implements VideoControlScreen.CanListenForUserScrubEvents {

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    private SeekBar sb;

    @Test
    public void userSeekToMSEventWhenScrubBarDragged() {
        fingerDownOnScrubber();
        scrubTo(100);
        fingerUpOnScrubber();
        assertThat("scrub event", scrubEvent,is(100l));
    }


    @Test
    public void onlyLastUserSeekToMSEventWhenScrubBarDraggedBackAndForth() {
        fingerDownOnScrubber();
        scrubTo(100);
        scrubTo(90);
        scrubTo(80);
        assertThat("scrub event", scrubEvent,is(NO_EVENT));
        scrubTo(90);
        scrubTo(100);
        scrubTo(120);
        fingerUpOnScrubber();
        assertThat("scrub event", scrubEvent,is(120l));
    }


    @Test
    public void noEventWhenScrubSetProgramaticly() {
        setScrubTo(100);
        assertThat("scrub event", scrubEvent,is(NO_EVENT));
    }


    @Before
    public void setUp() throws Exception {

        activity = ActivityTestSupport.createVisibleActivityForLayout(R.layout.videoplayer);
        VideoScreenViewRenderer lvideoScreen = new VideoScreenViewRenderer(activity.viewFinder());
        lvideoScreen.setScrubEventListener(this);
        videoScreen = lvideoScreen;

        sb = (SeekBar) activity.findViewById(R.id.seekBar);
        onSeekBarChangeListener = Robolectric.shadowOf(sb).getOnSeekBarChangeListener();
    }


    private VideoControlScreen videoScreen;

    private ActivityForTestingViews activity;

    private final long NO_EVENT = -1;
    private long scrubEvent = NO_EVENT;

    @Override
    public void userScrubbedTo(long positionAsPercentage) {
        scrubEvent = positionAsPercentage;
    }

    private void scrubTo(int AMOUNT_OF_PROGRESS) {
        boolean EVENT_FROM_USER = true;
        onSeekBarChangeListener.onProgressChanged(sb, AMOUNT_OF_PROGRESS, EVENT_FROM_USER);
    }

    private void fingerUpOnScrubber() {
        onSeekBarChangeListener.onStopTrackingTouch(sb);
    }

    private void fingerDownOnScrubber() {
        onSeekBarChangeListener.onStartTrackingTouch(sb);
    }

    private void setScrubTo(int i) {
        SeekBar sb = (SeekBar) activity.findViewById(R.id.seekBar);
        sb.setProgress(i);
    }

}
