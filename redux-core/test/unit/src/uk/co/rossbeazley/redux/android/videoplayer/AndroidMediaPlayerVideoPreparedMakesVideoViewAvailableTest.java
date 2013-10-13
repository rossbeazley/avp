package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.UriString;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class AndroidMediaPlayerVideoPreparedMakesVideoViewAvailableTest implements MediaPlayer {

    @Test
    public void refactor() {
        fail("Tests in this package need a serious look at, either delete some or merge");
    }


    private Collection<StateChangeListener> stateChangeListeners = new ArrayList<StateChangeListener>();
    private AndroidMediaPlayerVideoPreparer videoPreparer;

    private VideoView videoView;
    private VideoView videoViewFromMP = new VideoView() {};

    @Test
    public void whenMediaPlayerIsPreparedWeStartPlayback() {
        givenWeAreListeningForVideoViewCreation();
        andTheMediaPlayerThatBecomesPrepared();
        assertVideoViewCreated();
    }

    private void givenWeAreListeningForVideoViewCreation() {
        videoPreparer = new AndroidMediaPlayerVideoPreparer(aMPFactory());
        videoPreparer.addVideoLoadedListener(new VideoLoadedListener(){
            @Override
            public void videoLoaded(VideoView view) {
                videoView = view;
            }
        });
    }

    private void andTheMediaPlayerThatBecomesPrepared() {
        videoPreparer.playVideoUrl(UriString.from("ANY"));
    }

    private MediaPlayerFactory aMPFactory() {
        return new MediaPlayerFactory() {
            @Override
            public MediaPlayer createMediaPlayerForUri(UriString uri) {
                return AndroidMediaPlayerVideoPreparedMakesVideoViewAvailableTest.this;
            }
        };
    }

    private void assertVideoViewCreated() {
        assertThat(videoView,is(videoViewFromMP));
    }




    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        for(StateChangeListener stateChangeListener : stateChangeListeners) stateChangeListener.state(MediaPlayer.PREPARED);
    }

    @Override
    public void addStateChangeListener(StateChangeListener stateChangeListener) {
        this.stateChangeListeners.add(stateChangeListener);
    }

    @Override
    public void start() {}

    @Override
    public VideoView videoView() {
        return videoViewFromMP;
    }
}
