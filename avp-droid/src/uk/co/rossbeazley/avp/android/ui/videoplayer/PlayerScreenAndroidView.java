package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;

public final class PlayerScreenAndroidView implements VideoOutputScreen {

    private final CanFindViewById viewFinder;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;

    public PlayerScreenAndroidView(CanFindViewById canFindViewById) {
        this.viewFinder = canFindViewById;
        this.canListenForScreenTearDownEvents = Screen.CanListenForScreenTearDownEvents.NONE;
    }

    @Override
    public void attachVideo(RenderedVideoOutput videoOutput) {
        ViewGroup container = (ViewGroup) viewFinder.findViewById(R.id.videocontainer);
        videoOutput.attachToViewGroup(container);
    }

    @Override
    public void tearDown() {
        canListenForScreenTearDownEvents.screenTearDown();
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        this.canListenForScreenTearDownEvents = canListenForScreenTearDownEvents;
    }

}
