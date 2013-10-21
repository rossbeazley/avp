package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerControlTest {

    private static final boolean STOPPED = true;
    private static final boolean STARTED = false;
    private boolean playerState;

    @Test
    public void stopsTheMediaPlayerWhenAppHidden() {

        EventBus bus = new ExecutorEventBus();
        new MediaPlayerControl(bus);

        CanControlMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED);

        mediaPlayer.start();
        playerState = STARTED;

        bus.whenEvent(Events.PLAYER_STOPPED).thenRun(new Function() {
            @Override
            public void invoke() {
                playerState = STOPPED;
            }
        });

        bus.announce(Events.APP_HIDDEN);

        assertThat(playerState, is(STOPPED));
    }

}
