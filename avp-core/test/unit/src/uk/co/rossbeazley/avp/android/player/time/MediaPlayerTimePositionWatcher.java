package uk.co.rossbeazley.avp.android.player.time;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.TimeInMilliseconds;
import uk.co.rossbeazley.avp.android.mediaplayer.CanGetTimeFromMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;


public class MediaPlayerTimePositionWatcher {
    private final CanGetTimeFromMediaPlayer mediaPlayer;
    private final TimePositionEventsTest.CanExecuteCommandsAtFixedRate executor;
    private final EventBus bus;
    private Runnable checkTimeCommand;
    private final TimeInMilliseconds everySecond;
                                          //todo the media player should be passed in on a load video event
    public MediaPlayerTimePositionWatcher(CanGetTimeFromMediaPlayer aMediaPlayer, TimePositionEventsTest.CanExecuteCommandsAtFixedRate executor, EventBus bus) {
        this.mediaPlayer = aMediaPlayer;
        this.executor = executor;
        this.bus = bus;
        everySecond = new TimeInMilliseconds(1000);
        checkTimeCommand = new Runnable() {
            @Override
            public void run() {
                checkTheTime();
            }
        };
        monitorMediaPlayerTime();
    }

    private void checkTheTime() {
        TimeInMilliseconds currentPosition = mediaPlayer.getCurrentPosition();
        TimeInMilliseconds duration = mediaPlayer.getDuration();

        MediaTimePosition mediaTimePosition = new MediaTimePosition(currentPosition, duration);
        announce(mediaTimePosition);
    }

    private void announce(MediaTimePosition mediaTimePosition) {
        bus.sendPayload(mediaTimePosition).withEvent(Events.MEDIA_PLAYER_TIME_UPDATE);
    }

    private void monitorMediaPlayerTime() {
        executor.schedule(checkTimeCommand, everySecond);
    }
}
