package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.UriString;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerVideoWhenPreparedStartsPlaybackTest implements MediaPlayerFactory {


    public static final String STARTED = "STARTED";
    public static final String NOT_STARTED = "NOT STARTED";
    private static final VideoView UNUSED_VIEW = null;

    private String mediaPlayerPlaybackState = NOT_STARTED;

    @Test
    public void whenMediaPlayerIsPreparedWeStartPlayback() {
        MediaPlayerFactory mpFactory = new MediaPlayerFactory() {
            @Override
            public MediaPlayer createMediaPlayerForUri(UriString uri) {
                return FakeMediaPlayer.createFakeMediaPlayer();
            }
        };

        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(this, new FakeMediaPlayerViewFactory(UNUSED_VIEW));

        videoPreparer.addVideoLoadedListener(new VideoLoadedListener() {
            @Override
            public void videoLoaded(VideoView view) {
                mediaPlayerPlaybackState = STARTED;
            }
        });

        videoPreparer.playVideoUrl(UriString.from("ANY"));

        assertThat(mediaPlayerPlaybackState,is(STARTED));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return FakeMediaPlayer.createFakeMediaPlayer();
    }
}
