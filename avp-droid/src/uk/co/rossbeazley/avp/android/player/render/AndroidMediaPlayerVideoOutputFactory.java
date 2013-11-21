package uk.co.rossbeazley.avp.android.player.render;

public class AndroidMediaPlayerVideoOutputFactory implements CanCreateAndroidMediaPlayerVideoOutput {

    @Override
    public RenderedVideoOutput createAndroidMediaPlayerVideoOutput(CanAttachToAndroidView mediaPlayer) {
        return new AndroidMediaPlayerVideoOutput(mediaPlayer);
    }
}