package uk.co.rossbeazley.avp.android.mediaplayer;

public interface CanPrepareMediaPlayer {
    void prepareAsync();

    void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener);

    PreparedState PREPARED = new PreparedState();

    public interface PreparedStateChangeListener {
        void state(PreparedState prepared);
    }

    public static class PreparedState { }
}
