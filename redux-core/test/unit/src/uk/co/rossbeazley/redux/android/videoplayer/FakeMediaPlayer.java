package uk.co.rossbeazley.redux.android.videoplayer;

import java.util.ArrayList;
import java.util.Collection;

class FakeMediaPlayer implements MediaPlayer {

    private VideoView videoView;
    private VideoView preparedVideoView;

    private Collection<StateChangeListener> stateChangeListeners = new ArrayList<StateChangeListener>();

    public FakeMediaPlayer() {
    }

    private FakeMediaPlayer(VideoView videoView) {
        this.videoView = videoView;
    }

    public static FakeMediaPlayer createFakeMediaPlayerThatReturnsVideoView(VideoView videoView) {
        return new FakeMediaPlayer(videoView);
    }

    public static MediaPlayer createFakeMediaPlayer() {
        return new FakeMediaPlayer();
    }
    @Override
    public void prepareAsync() {
        changeStraightToPreparedState();
    }

    private void changeStraightToPreparedState() {
        preparedVideoView = videoView;
        for(StateChangeListener stateChangeListener : this.stateChangeListeners) {
            stateChangeListener.state(MediaPlayer.PREPARED);
        }
    }

    @Override
    public void addStateChangeListener(StateChangeListener stateChangeListener) {
        this.stateChangeListeners.add(stateChangeListener);
    }

    @Override
    public void start() {
        // think i should send a started state change with the createVideoView in it
        // also, it shouldnt be called a createVideoView,
    }

    @Override
    public VideoView createVideoView() {
        return this.preparedVideoView;
    }


}
