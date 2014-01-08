package uk.co.rossbeazley.avp.android.player.control;

public interface CanControlMediaPlayer {
    void start();
    boolean isPlaying();

    void stop();
    void pause();
    boolean isNotPlaying();

    CanControlMediaPlayer NULL = new CanControlMediaPlayer() {
        public void start() { }
        public boolean isPlaying() {
            return false;
        }
        public void stop() { }
        public void pause() { }
        public boolean isNotPlaying() {
            return true;
        }
    };
}
