package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.UriString;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class AndroidMediaPlayerVideoPreparedMakesVideoViewAvailableTest implements MediaPlayerFactory {

    private VideoView videoView;
    private VideoView videoViewFromMP = new VideoView() {};

    @Test
    public void whenMediaPlayerIsPreparedWeStartPlayback() {
        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(this);
        videoPreparer.addVideoLoadedListener(new VideoLoadedListener() {
            @Override
            public void videoLoaded(VideoView view) {
                videoView = view;
            }
        });

        videoPreparer.playVideoUrl(UriString.from("ANY"));

        assertThat(videoView,is(videoViewFromMP));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return FakeMediaPlayer.createFakeMediaPlayerThatReturnsVideoView(videoViewFromMP);
    }
}
