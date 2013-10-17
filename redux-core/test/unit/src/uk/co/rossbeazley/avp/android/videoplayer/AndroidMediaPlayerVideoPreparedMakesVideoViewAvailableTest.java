package uk.co.rossbeazley.avp.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.redux.android.videoplayer.MediaPlayerViewFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

//TODO move view creation to android module and maybe respond to prepared event
public class AndroidMediaPlayerVideoPreparedMakesVideoViewAvailableTest implements MediaPlayerFactory {

    private VideoView videoView;
    private VideoView videoViewFromMP = new VideoView() {};

    @Test
    public void whenMediaPlayerIsPreparedWeStartPlayback() {
        MediaPlayerViewFactory mpViewFactory = new FakeMediaPlayerViewFactory(videoViewFromMP);

        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(this, mpViewFactory);
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
        return FakeMediaPlayer.createFakeMediaPlayer();
    }

}
