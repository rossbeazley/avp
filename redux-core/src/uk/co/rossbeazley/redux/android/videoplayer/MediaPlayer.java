package uk.co.rossbeazley.redux.android.videoplayer;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer {
    void prepareAsync();

    void addStateChangeListener(StateChangeListener stateChangeListener);


    final PreparedState PREPARED = new PreparedState();

    void start();

    VideoView videoView();

    public interface StateChangeListener {
        void state(PreparedState prepared);
    }

    public static class PreparedState { }
}
