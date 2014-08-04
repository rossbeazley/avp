package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;

public class VideoScreenAndroidView implements VideoPlayerScreen {
    private final ViewFinder viewFinder;

    private CanListenForUserPlayEvents canListenForUserPlayEvents;
    private CanListenForUserPauseEvents canListenForUserPauseEvents;
    private CanListenForUserScrubEvents canListenForUserScrubEvents;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;


    public VideoScreenAndroidView(CanFindViewById canFindViewById) {
        this.canListenForUserPlayEvents = CanListenForUserPlayEvents.NONE;
        this.canListenForUserPauseEvents = CanListenForUserPauseEvents.NONE;
        this.canListenForUserScrubEvents = CanListenForUserScrubEvents.NONE;
        this.canListenForScreenTearDownEvents = CanListenForScreenTearDownEvents.NONE;
        viewFinder = new ViewFinder(canFindViewById);

        bind();
    }
    /// ---------- Event adapter

    private void bind() {

        //we need our buttons to be bound for control - as in classic mvc
        bindPlayButton();
        bindPauseButton();
        bindSeekBar();

        //and then we expose more view style logic, how to show stuff
    }

    private void bindPauseButton() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                canListenForUserPauseEvents.userPressedPause();
            }
        };
        viewFinder.setOnClickListener(onClickListener, R.id.pause);
    }

    private void bindPlayButton() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                canListenForUserPlayEvents.userPressedPlay();
            }
        };
        viewFinder.setOnClickListener(onClickListener, R.id.play);
    }

    private void bindSeekBar() {
        SeekBar seekBar = (SeekBar) viewFinder.find(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new ScrubEventAdapter());
    }

    @Override
    public void setPauseEventListener(CanListenForUserPauseEvents canListenForUserPauseEvents) {
        this.canListenForUserPauseEvents = canListenForUserPauseEvents;
    }

    @Override
    public void setPlayEventListener(CanListenForUserPlayEvents canListenForUserPlayEvents) {
        this.canListenForUserPlayEvents = canListenForUserPlayEvents;
    }

    @Override
    public void setScrubEventListener(CanListenForUserScrubEvents canListenForUserScrubEvents) {
        this.canListenForUserScrubEvents = canListenForUserScrubEvents;
    }


    /// ---------- End of event adapter

    @Override
    public void showSeekBarPosition(long position, long max) {
        SeekBar seekBar = (SeekBar) viewFinder.find(R.id.seekBar);
        seekBar.setProgress((int) position);
        seekBar.setMax((int) max);
//        seekBar.setSecondaryProgress((int) (position*2));
    }

    @Override
    public void showTotalTime(TimeInMilliseconds time) {
        viewFinder.setText(String.valueOf(time.asMinutesAndSeconds()), R.id.totaltime);
    }

    @Override
    public void showProgressTime(TimeInMilliseconds time) {
        viewFinder.setText(String.valueOf(time.asMinutesAndSeconds()), R.id.currenttime);
    }

    @Override
    public void showBuffering() {
        viewFinder.setVisibility(View.VISIBLE, R.id.buffering);
    }

    @Override
    public void hideBuffering() {
        viewFinder.setVisibility(View.GONE, R.id.buffering);
    }

    @Override
    public void showPlay() {
        viewFinder.setVisibility(View.VISIBLE, R.id.play);
        viewFinder.setVisibility(View.GONE, R.id.pause);
    }

    @Override
    public void showPause() {
        viewFinder.setVisibility(View.GONE, R.id.play);
        viewFinder.setVisibility(View.VISIBLE, R.id.pause);
    }

    @Override
    public void tearDown() {

        viewFinder.clearOnClickListener(R.id.play);

        viewFinder.clearOnClickListener(R.id.pause);

        SeekBar seekBar = (SeekBar) viewFinder.find(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(null);

        canListenForScreenTearDownEvents.screenTearDown();
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        this.canListenForScreenTearDownEvents = canListenForScreenTearDownEvents;
    }

    private class ScrubEventAdapter implements SeekBar.OnSeekBarChangeListener {

        public Integer last_seek_position;

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean user_event) {
            if (user_event) {
                last_seek_position = i;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            last_seek_position = null;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (last_seek_position != null) {
                canListenForUserScrubEvents.userScrubbedTo(last_seek_position);
                last_seek_position = null;
            }
        }
    }
}
