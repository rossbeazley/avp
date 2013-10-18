package uk.co.rossbeazley.avp.android.player.ui;

import android.widget.SeekBar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.AndroidVideoScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.CanListenForUserScrubEvents;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoScreen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ScreenScrubTest implements CanListenForUserScrubEvents {

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    private SeekBar sb;

    @Test
    public void userSeekToMSEventWhenScrubBarDragged() {
        fingerDownOnScrubber();
        scrubTo(1000);
        fingerUpOnScrubber();
        assertThat("scrub event", scrubEvent,is(1000));
    }


    @Test
    public void onlyLastUserSeekToMSEventWhenScrubBarDraggedBackAndForth() {
        fingerDownOnScrubber();
        scrubTo(1000);
        scrubTo(900);
        scrubTo(800);
        assertThat("scrub event", scrubEvent,is(NO_EVENT));
        scrubTo(900);
        scrubTo(1000);
        scrubTo(1200);
        fingerUpOnScrubber();
        assertThat("scrub event", scrubEvent,is(1200));
    }


    @Test
    public void noEventWhenScrubSetProgramaticly() {
        setScrubTo(1000);
        assertThat("scrub event", scrubEvent,is(NO_EVENT));
    }


    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(ActivityForTestingViews.class).create().start().visible().get();
        AndroidVideoScreen lvideoScreen = new AndroidVideoScreen(activity.layoutInflater(), activity.viewFinder());
        lvideoScreen.setScrubEventListener(this);
        videoScreen = lvideoScreen;
        videoScreen.bind();

        sb = (SeekBar) activity.findViewById(R.id.seekBar);
        onSeekBarChangeListener = Robolectric.shadowOf(sb).getOnSeekBarChangeListener();
    }


    private VideoScreen videoScreen;

    private ActivityForTestingViews activity;

    private final Integer NO_EVENT = null;
    private Integer scrubEvent = NO_EVENT;

    @Override
    public void userScrubbedTo(int time) {
        scrubEvent = time;
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
