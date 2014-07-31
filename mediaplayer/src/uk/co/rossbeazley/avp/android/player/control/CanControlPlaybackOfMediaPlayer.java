package uk.co.rossbeazley.avp.android.player.control;

public interface CanControlPlaybackOfMediaPlayer {
    void start();
    void stop();
    void pause();

    CanControlPlaybackOfMediaPlayer NULL = new CanControlPlaybackOfMediaPlayer() {
        public void start() { }
        public void stop() { }
        public void pause() { }
    };
}
