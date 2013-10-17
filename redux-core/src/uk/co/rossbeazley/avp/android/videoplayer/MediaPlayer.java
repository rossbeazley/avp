package uk.co.rossbeazley.avp.android.videoplayer;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer {
    void prepareAsync();

    void addStateChangeListener(StateChangeListener stateChangeListener);

    void start();

    final PreparedState PREPARED = new PreparedState();

    public interface StateChangeListener {
        void state(PreparedState prepared);
    }

    public static class PreparedState { }
}
