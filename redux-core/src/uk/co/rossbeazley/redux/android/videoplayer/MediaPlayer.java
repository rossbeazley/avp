package uk.co.rossbeazley.redux.android.videoplayer;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer {
    void prepareAsync();

    void addStateChangeListener(StateChangeListener stateChangeListener);

    void start();

    VideoView videoView();

    final PreparedState PREPARED = new PreparedState();

    public interface StateChangeListener {
        void state(PreparedState prepared);
    }

    public static class PreparedState { }
}
