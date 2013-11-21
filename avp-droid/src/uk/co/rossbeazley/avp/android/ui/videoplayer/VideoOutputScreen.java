package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.player.render.RenderedVideoOutput;

public interface VideoOutputScreen {
    //RenderedVideoOutput is the only thing keeping this class in the droid project
    void attachVideo(RenderedVideoOutput videoOutput);
}
