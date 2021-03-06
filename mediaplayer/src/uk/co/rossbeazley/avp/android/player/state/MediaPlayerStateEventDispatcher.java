package uk.co.rossbeazley.avp.android.player.state;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class MediaPlayerStateEventDispatcher {

    public static final TimeInMilliseconds ONE_HUNDRED_MILLISECONDS = TimeInMilliseconds.fromLong(100);
    private final CanExecuteCommandsAtFixedRate scheduler;
    MediaPlayerStateMachine mediaPlayerStateMachine;

    public MediaPlayerStateEventDispatcher(EventBus bus, CanExecuteCommandsAtFixedRate scheduler) {
        this.scheduler = scheduler;
        bindVideoLoadedEvent(bus);
    }

    private void bindVideoLoadedEvent(final EventBus bus) {
        bus.whenEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanDiscoverPlayingStateOfMediaPlayer>() {
                    @Override
                    public void invoke(CanDiscoverPlayingStateOfMediaPlayer payload) {
                        mediaPlayerStateMachine = new MediaPlayerStateMachine(payload,  bus);
                        scheduler.schedule(checkStateRunnable, ONE_HUNDRED_MILLISECONDS);
                    }
                });
    }

    private Runnable checkStateRunnable = new Runnable() {
        @Override
        public void run() {
            checkMediaPlayerState();
        }
    };

    private void checkMediaPlayerState() {
        mediaPlayerStateMachine.tick();
    }
}
