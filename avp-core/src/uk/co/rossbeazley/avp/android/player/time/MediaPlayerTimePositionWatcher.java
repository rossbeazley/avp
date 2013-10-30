package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.mediaplayer.CanGetTimeFromMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;


public class MediaPlayerTimePositionWatcher {
    private CanGetTimeFromMediaPlayer mediaPlayer;
    private final CanExecuteCommandsAtFixedRate executor;
    private final EventBus bus;
    private Runnable checkTimeCommand;
    private final TimeInMilliseconds everySecond;
    private MediaTimePosition rememberedTimePosition;

    public MediaPlayerTimePositionWatcher(CanExecuteCommandsAtFixedRate executor, EventBus bus) {
        this.executor = executor;
        this.bus = bus;
        rememberedTimePosition = null;
        everySecond = new TimeInMilliseconds(1000);
        checkTimeCommand = new Runnable() {
            @Override
            public void run() {
                checkTheTime();
            }
        };

        bindVideoLoadedEvent(bus);

    }

    private void bindVideoLoadedEvent(EventBus bus) {
        bus.whenEvent(Events.VIDEO_LOADED)
                .thenRun(new FunctionWithParameter<CanGetTimeFromMediaPlayer>() {

                    @Override
                    public void invoke(CanGetTimeFromMediaPlayer payload) {
                        monitorMediaPlayerTime(payload);
                    }
                });
    }

    private void checkTheTime() {
        TimeInMilliseconds currentPosition = mediaPlayer.getCurrentPosition();
        TimeInMilliseconds duration = mediaPlayer.getDuration();

        MediaTimePosition mediaTimePosition = new MediaTimePosition(currentPosition, duration);
        announceIfDifferentToLastTime(mediaTimePosition);
    }

    private void announceIfDifferentToLastTime(MediaTimePosition mediaTimePosition) {
        if(notTheSamePositionAsLastTime(mediaTimePosition)) {
            rememberedTimePosition = mediaTimePosition;
            sendAnnouncement(mediaTimePosition);
        }
    }

    private void sendAnnouncement(MediaTimePosition mediaTimePosition) {
        bus.sendPayload(mediaTimePosition).withEvent(Events.MEDIA_PLAYER_TIME_UPDATE);
    }

    private boolean notTheSamePositionAsLastTime(MediaTimePosition mediaTimePosition) {
        if(rememberedTimePosition == null) return true;
        return ! rememberedTimePosition.equals(mediaTimePosition);
    }

    private void monitorMediaPlayerTime(CanGetTimeFromMediaPlayer player) {
        mediaPlayer = player;
        executor.schedule(checkTimeCommand, everySecond);
    }
}
