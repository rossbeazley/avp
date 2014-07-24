package uk.co.rossbeazley.avp.android.player.state;

import uk.co.rossbeazley.avp.eventbus.EventBus;

public class MediaPlayerStateMachine {
    public static final String PLAYER_PLAYING = "player_playing";
    public static final String PLAYER_PAUSED = "player_paused";
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
        bus.announce(PLAYER_PAUSED);
    }

    private void transitionToPlaying() {
        state = playingState;
        bus.announce(PLAYER_PLAYING);
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
