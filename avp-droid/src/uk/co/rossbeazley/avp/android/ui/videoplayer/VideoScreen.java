package uk.co.rossbeazley.avp.android.ui.videoplayer;

public interface VideoScreen {
    //RenderedVideoOutput is the only thing keeping this class in the droid project
    void attachVideo(RenderedVideoOutput videoOutput);
}
