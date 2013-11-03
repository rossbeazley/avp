package uk.co.rossbeazley.avp.android.mediaplayer;

public interface CanControlMediaPlayer {
    void start();
    boolean isPlaying();

    void stop();
    void pause();
    boolean isNotPlaying();
}
