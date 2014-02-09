package uk.co.rossbeazley.avp.android.player.control;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerControl {


    private final EventBus bus;
    private CanControlPlaybackOfMediaPlayer mediaPlayer;

    public MediaPlayerControl(EventBus bus) {
        mediaPlayer = CanControlPlaybackOfMediaPlayer.NULL;
        this.bus = bus;
        handleVideoLoaded();
        handleAppHidden();
        handleUserPause();
        handleUserPlay();
        handleUserExitVideoScreen();
    }

    private void handleUserExitVideoScreen() {
        bus.whenEvent(Events.USER_EXIT_VIDEO_SCREEN).thenRun(new Function() {
            @Override
            public void invoke() {
                stopMediaPlayer();
            }
        });
    }

    private void handleUserPlay() {
        bus.whenEvent(Events.USER_PLAY).thenRun(new Function() {
            @Override
            public void invoke() {
                playMediaPlayer();
            }
        });
    }

    private void playMediaPlayer() {
        mediaPlayer.start();
    }

    private void handleUserPause() {
        bus.whenEvent(Events.USER_PAUSE).thenRun(new Function() {
            @Override
            public void invoke() {
                pauseMediaPlayer();
            }
        });
    }

    private void pauseMediaPlayer() {
        mediaPlayer.pause();
    }

    private void handleAppHidden() {
        bus.whenEvent(Events.APP_HIDDEN).thenRun(new Function() {
            @Override
            public void invoke() {
                stopMediaPlayer();
            }
        });
    }

    private void stopMediaPlayer() {
        mediaPlayer.stop();
        bus.announce(Events.PLAYER_STOPPED); //TODO move this out of control class into state observer class
    }

    private void handleVideoLoaded() {
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<CanControlPlaybackOfMediaPlayer>() {
            @Override
            public void invoke(CanControlPlaybackOfMediaPlayer payload) {
                mediaPlayer = payload;
            }
        });
    }
}
