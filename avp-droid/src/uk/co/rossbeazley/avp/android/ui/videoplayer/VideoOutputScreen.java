package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;
import uk.co.rossbeazley.avp.android.ui.Screen;

public interface VideoOutputScreen extends Screen {
    void attachVideo(RenderedVideoOutput videoOutput);
}
