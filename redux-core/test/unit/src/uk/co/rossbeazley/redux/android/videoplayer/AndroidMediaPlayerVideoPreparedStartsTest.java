package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.UriString;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerVideoPreparedStartsTest implements MediaPlayer {


    public static final String STARTED = "STARTED";
    private Collection<StateChangeListener> stateChangeListeners = new ArrayList<StateChangeListener>();
    private String state = "NOT STARTED";

    @Test
    public void whenMediaPlayerIsPreparedWeStartPlayback() {
        givenAMediaPlayerThatBecomesPrepared();
        assertWeStartPlayback();
    }

    private void givenAMediaPlayerThatBecomesPrepared() {
        AndroidMediaPlayerVideoPreparer videoPreparer = new AndroidMediaPlayerVideoPreparer(aMPFactory());
        videoPreparer.playVideoUrl(UriString.from("ANY"));
    }

    private MediaPlayerFactory aMPFactory() {
        return new MediaPlayerFactory() {
            @Override
            public MediaPlayer createMediaPlayerForUri(UriString uri) {
                return AndroidMediaPlayerVideoPreparedStartsTest.this;
            }
        };
    }

    private void assertWeStartPlayback() {
        assertThat(state,is(STARTED));
    }




    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        for(StateChangeListener stateChangeListener : stateChangeListeners) stateChangeListener.state(MediaPlayer.PREPARED);
    }

    @Override
    public void addStateChangeListener(MediaPlayer.StateChangeListener stateChangeListener) {
        this.stateChangeListeners.add(stateChangeListener);
    }

    @Override
    public void start() {
        this.state = STARTED;
    }
}
