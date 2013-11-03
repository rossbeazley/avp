package uk.co.rossbeazley.avp.android.player.control;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class MediaPlayerStateMachine {
    private MediaPlayerState state;
    private EventBus bus;

    public MediaPlayerStateMachine(EventBus bus) {
        this.bus = bus;
    }

    void setupInitialStateFor(CanControlMediaPlayer mediaPlayer) {
        state = mediaPlayer.isPlaying() ? playingState : pausedState;
    }

    void check(CanControlMediaPlayer mediaPlayer) {
        state.check(mediaPlayer);
    }

    private void transitionToPaused() {
        state = pausedState;
        bus.announce(Events.PLAYER_PAUSED);

    }

    private void transitionToPlaying() {
        state = playingState;
        bus.announce(Events.PLAYER_PLAYING);
    }

    private interface MediaPlayerState {
        void check(CanControlMediaPlayer mediaPlayer);
    }

    MediaPlayerState playingState = new MediaPlayerState() {
        @Override
        public void check(CanControlMediaPlayer mediaPlayer) {
            if (mediaPlayer.isNotPlaying()) {
                transitionToPaused();
            }
        }
    };

    MediaPlayerState pausedState = new MediaPlayerState() {
        @Override
        public void check(CanControlMediaPlayer mediaPlayer) {
            if (mediaPlayer.isPlaying()) {
                transitionToPlaying();
            }
        }
    };
}