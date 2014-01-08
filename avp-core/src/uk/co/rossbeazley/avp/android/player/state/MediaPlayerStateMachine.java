package uk.co.rossbeazley.avp.android.player.state;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.control.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;

class MediaPlayerStateMachine {
    private MediaPlayerState state;
    private final CanControlMediaPlayer mediaPlayer;
    private EventBus bus;

    public MediaPlayerStateMachine(CanControlMediaPlayer mediaPlayer, EventBus bus) {
        this.mediaPlayer = mediaPlayer;
        this.bus = bus;
        this.state = initialState;
    }

    void tick() {
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

    MediaPlayerState initialState = new MediaPlayerState() {
        @Override
        public void check(CanControlMediaPlayer mediaPlayer) {
            if (mediaPlayer.isPlaying() ) {
                transitionToPlaying();
            } else {
                transitionToPaused();
            }
        }
    };

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
