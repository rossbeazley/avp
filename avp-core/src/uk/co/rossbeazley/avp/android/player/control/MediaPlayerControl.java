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
        this.bus = bus;
        handleVideoLoaded();
        handleAppHidden();
        handleUserPause();
    }

    private void handleUserPause() {
        bus.whenEvent(Events.PAUSE).thenRun(new Function() {
            @Override
            public void invoke() {
                pauseMediaPlayer();
            }
        });
    }

    private void pauseMediaPlayer() {
        mediaPlayer.pause();
        bus.announce(Events.PLAYER_PAUSED);
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
        bus.whenEvent(Events.VIDEO_LOADED).thenRun(new FunctionWithParameter<CanControlMediaPlayer>() {
            @Override
            public void invoke(CanControlMediaPlayer payload) {
                mediaPlayer = payload;
            }
        });
    }
}
