package uk.co.rossbeazley.avp.android.videoplayer;

import uk.co.rossbeazley.avp.android.videoplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.videoplayer.VideoView;
import uk.co.rossbeazley.redux.android.videoplayer.MediaPlayerViewFactory;

class FakeMediaPlayerViewFactory implements MediaPlayerViewFactory {
    private final VideoView videoViewFromMP;

    public FakeMediaPlayerViewFactory(VideoView videoViewFromMP) {
        this.videoViewFromMP = videoViewFromMP;
    }

    @Override
    public VideoView createVideoView(MediaPlayer mediaPlayer) {
        return this.videoViewFromMP;
    }
}
