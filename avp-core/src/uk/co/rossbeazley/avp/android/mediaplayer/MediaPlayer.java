package uk.co.rossbeazley.avp.android.mediaplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer extends CanPrepareMediaPlayer, CanControlMediaPlayer, CanGetTimeFromMediaPlayer {

    MediaPlayer NULL = new MediaPlayer(){
        @Override
        public void start() {}

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public boolean isStopped() {
            return false;
        }

        @Override
        public void stop() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void prepareAsync() {}

        @Override
        public void addPreparedStateChangeListener(PreparedStateChangeListener preparedStateChangeListener) {}

        @Override
        public TimeInMilliseconds getCurrentPosition() {
            return new TimeInMilliseconds(0);
        }
    };

}
