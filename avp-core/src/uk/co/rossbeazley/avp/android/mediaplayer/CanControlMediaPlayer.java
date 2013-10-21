package uk.co.rossbeazley.avp.android.mediaplayer;

public interface CanControlMediaPlayer {
    void start();
    boolean isPlaying();

    boolean isStopped();

    void stop();
}
