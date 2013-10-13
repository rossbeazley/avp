package uk.co.rossbeazley.redux.android.videoplayer;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
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
