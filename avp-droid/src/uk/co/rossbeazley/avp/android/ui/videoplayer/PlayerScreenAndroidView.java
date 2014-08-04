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

public class PlayerScreenAndroidView implements VideoOutputScreen {

    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;
    private final ViewFinder viewFinder;


    public PlayerScreenAndroidView(CanFindViewById canFindViewById) {
        viewFinder = new ViewFinder(canFindViewById);
        this.canListenForScreenTearDownEvents = Screen.CanListenForScreenTearDownEvents.NONE;

    }

    @Override
    public void attachVideo(RenderedVideoOutput videoOutput) {
        ViewGroup container = (ViewGroup) viewFinder.find(R.id.videocontainer);
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
