package uk.co.rossbeazley.avp.android.mediaplayer;

public interface CanPrepareMediaPlayer {
    void prepareAsync();

    void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener);

    public interface PreparedStateChangeListener {
        void prepared();
    }
}
