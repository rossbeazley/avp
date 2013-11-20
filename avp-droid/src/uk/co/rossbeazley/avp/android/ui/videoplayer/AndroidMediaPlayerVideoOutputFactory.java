package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.mediaplayer.CanAttachToAndroidView;

public class AndroidMediaPlayerVideoOutputFactory implements CanCreateAndroidMediaPlayerVideoOutput {

    @Override
    public RenderedVideoOutput createAndroidMediaPlayerVideoOutput(CanAttachToAndroidView mediaPlayer) {
        return new AndroidMediaPlayerVideoOutput(mediaPlayer);
    }
}