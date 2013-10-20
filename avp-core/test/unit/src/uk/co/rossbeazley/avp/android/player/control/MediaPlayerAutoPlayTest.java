package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerAutoPlayTest {

    @Test
    public void autoStartsTheMediaPlayerWhenVideoLoaded() {

        EventBus bus = new ExecutorEventBus();

        new MediaPlayerAutoPlay(bus);

        CanControlMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED);

        //I think this should be asserting on an event generation in response this state change
        assertThat(mediaPlayer.isPlaying(), is(true));

    }

}
