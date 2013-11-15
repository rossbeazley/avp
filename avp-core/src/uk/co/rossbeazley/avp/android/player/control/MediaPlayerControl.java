package uk.co.rossbeazley.avp.android.player.control;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerControl {


    private final EventBus bus;
    private CanControlMediaPlayer mediaPlayer;

    public MediaPlayerControl(EventBus bus) {
        mediaPlayer = CanControlMediaPlayer.NULL;
        this.bus = bus;
        handleVideoLoaded();
        handleAppHidden();
        handleUserPause();
        handleUserPlay();
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
        bus.announce(Events.PLAYER_STOPPED);
    }

    private void handleVideoLoaded() {
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED).thenRun(new FunctionWithParameter<CanControlMediaPlayer>() {
            @Override
            public void invoke(CanControlMediaPlayer payload) {
                mediaPlayer = payload;
            }
        });
    }
}
