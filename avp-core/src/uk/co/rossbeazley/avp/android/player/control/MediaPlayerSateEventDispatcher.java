package uk.co.rossbeazley.avp.android.player.control;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerSateEventDispatcher {

    public static final TimeInMilliseconds ONE_HUNDRED_MILLISECONDS = new TimeInMilliseconds(100);
    private final CanExecuteCommandsAtFixedRate scheduler;
    MediaPlayerStateMachine mediaPlayerStateMachine;
    CanControlMediaPlayer mediaPlayer;
    private Runnable checkStateRunnable = new Runnable() {
        @Override
        public void run() {
            checkMediaPlayerState();
        }
    };

    public MediaPlayerSateEventDispatcher(EventBus bus, CanExecuteCommandsAtFixedRate scheduler) {
        this.scheduler = scheduler;
        mediaPlayerStateMachine = new MediaPlayerStateMachine(bus);
        bindVideoLoadedEvent(bus);
    }

    private void bindVideoLoadedEvent(EventBus bus) {
        bus.whenEvent(Events.PLAYER_VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanControlMediaPlayer>() {
                    @Override
                    public void invoke(CanControlMediaPlayer payload) {
                        mediaPlayer = payload;
                        mediaPlayerStateMachine.setupInitialStateFor(payload);
                        scheduler.schedule(checkStateRunnable, ONE_HUNDRED_MILLISECONDS);
                    }
                });
    }

    private void checkMediaPlayerState() {
        mediaPlayerStateMachine.check(mediaPlayer);
    }


}
