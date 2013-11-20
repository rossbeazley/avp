package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.mediaplayer.CanAttachToAndroidView;

public interface CanCreateAndroidMediaPlayerVideoOutput {
    RenderedVideoOutput createAndroidMediaPlayerVideoOutput(CanAttachToAndroidView mediaPlayer);
}
