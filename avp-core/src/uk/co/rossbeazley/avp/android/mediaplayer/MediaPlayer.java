package uk.co.rossbeazley.avp.android.mediaplayer;

import uk.co.rossbeazley.avp.TimeInMilliseconds;

//REFACTOR, maybe i should segregate this interface
public interface MediaPlayer extends CanPrepareMediaPlayer, CanControlMediaPlayer, CanGetTimeFromMediaPlayer, CanScrubMediaPlayer {

    MediaPlayer NULL = new MediaPlayer(){

        private final TimeInMilliseconds noTime = TimeInMilliseconds.fromLong(0);

        @Override
        public void start() {}

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public boolean isNotPlaying() {
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
            return noTime;
        }

        @Override
        public TimeInMilliseconds getDuration() {
            return noTime;
        }

        @Override
        public void seekTo(TimeInMilliseconds time) {

        }

        @Override
        public void addScrubCompleteListener(ScrubCompleteListener listener) {
        }
    };

}
