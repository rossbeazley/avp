package uk.co.rossbeazley.avp.android.mediaplayer;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer extends CanPrepareMediaPlayer, CanControlMediaPlayer {

    MediaPlayer NULL = new MediaPlayer(){
        @Override
        public void start() {}

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public void prepareAsync() {}

        @Override
        public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {}
    };
}
