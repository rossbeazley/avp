package uk.co.rossbeazley.avp.android.player.state;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;

class MediaPlayerStateMachine {
    private MediaPlayerState state;
    private final CanDiscoverPlayingStateOfMediaPlayer mediaPlayer;
    private EventBus bus;

    public MediaPlayerStateMachine(CanDiscoverPlayingStateOfMediaPlayer mediaPlayer, EventBus bus) {
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
        void check(CanDiscoverPlayingStateOfMediaPlayer mediaPlayer);
    }

    MediaPlayerState initialState = new MediaPlayerState() {
        @Override
        public void check(CanDiscoverPlayingStateOfMediaPlayer mediaPlayer) {
            if (mediaPlayer.isPlaying() ) {
                transitionToPlaying();
            } else {
                transitionToPaused();
            }
        }
    };

    MediaPlayerState playingState = new MediaPlayerState() {
        @Override
        public void check(CanDiscoverPlayingStateOfMediaPlayer mediaPlayer) {
            if (mediaPlayer.isNotPlaying()) {
                transitionToPaused();
            }
        }
    };

    MediaPlayerState pausedState = new MediaPlayerState() {
        @Override
        public void check(CanDiscoverPlayingStateOfMediaPlayer mediaPlayer) {
            if (mediaPlayer.isPlaying()) {
                transitionToPlaying();
            }
        }
    };

}
