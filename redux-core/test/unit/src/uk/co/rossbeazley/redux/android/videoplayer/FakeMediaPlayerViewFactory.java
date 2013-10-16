package uk.co.rossbeazley.redux.android.videoplayer;

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
