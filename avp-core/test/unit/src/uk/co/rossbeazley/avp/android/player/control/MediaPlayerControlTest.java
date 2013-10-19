package uk.co.rossbeazley.avp.android.player.control;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.mediaplayer.CanControlMediaPlayer;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaPlayerControlTest {

    @Test
    public void autoStartsTheMediaPlayerWhenVideoLoaded() {

        EventBus bus = new ExecutorEventBus();

        new MediaPlayerAutoPlay(bus);

        CanControlMediaPlayer mediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        bus.sendPayload(mediaPlayer).withEvent(Events.VIDEO_LOADED);

        //I think this should be asserting on an event generation in response this state change
        assertThat(mediaPlayer.isPlaying(), is(true));

    }

    private class MediaPlayerAutoPlay {
        public MediaPlayerAutoPlay(EventBus bus) {
            loadVideoEvent(bus);
        }

        private void loadVideoEvent(EventBus bus) {
            bus.whenEvent(Events.VIDEO_LOADED).thenRun(new FunctionWithParameter<CanControlMediaPlayer>() {
                @Override
                public void invoke(CanControlMediaPlayer payload) {
                    payload.start();
                }
            });
        }
    }
}
