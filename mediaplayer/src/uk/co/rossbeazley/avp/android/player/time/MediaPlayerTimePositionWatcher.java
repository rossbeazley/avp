package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.player.preparer.MediaPlayerPreparer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;


public class MediaPlayerTimePositionWatcher {
    public static final String PLAYER_TIME_UPDATE = "mediaTimeUpdate";
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
        everySecond = TimeInMilliseconds.fromLong(1000);
        checkTimeCommand = new Runnable() {
            @Override
            public void run() {
                checkTheTime();
            }
        };

        bindVideoLoadedEvent(bus);

    }

    private void bindVideoLoadedEvent(EventBus bus) {
        bus.whenEvent(MediaPlayerPreparer.PLAYER_VIDEO_LOADED)
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
        bus.sendPayload(mediaTimePosition).withEvent(PLAYER_TIME_UPDATE);
    }

    private boolean notTheSamePositionAsLastTime(MediaTimePosition mediaTimePosition) {
        return rememberedTimePosition == null ? true : !rememberedTimePosition.equals(mediaTimePosition);
    }

    private void monitorMediaPlayerTime(CanGetTimeFromMediaPlayer player) {
        mediaPlayer = player;
        executor.schedule(checkTimeCommand, everySecond);
    }
}
