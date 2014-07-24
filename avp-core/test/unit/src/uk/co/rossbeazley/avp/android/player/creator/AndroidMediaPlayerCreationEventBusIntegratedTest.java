package uk.co.rossbeazley.avp.android.player.creator;

import org.junit.Test;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.player.MediaPlaybackService;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerCreationEventBusIntegratedTest implements MediaPlayerFactory {
    private Object fakeMediaPlayer;
    private Object announcedMediaPlayer;

    @Test
    public void createsMediaPlayerAndTellsEveryone() {

        EventBus bus = new ExecutorEventBus();

        MediaPlayerFactory mediaPlayerFactory = (MediaPlayerFactory) this;
        new MediaPlayerCreator(bus, mediaPlayerFactory);

        bus.whenEvent(MediaPlayerCreator.PLAYER_CREATED).thenRun(new FunctionWithParameter<Object>() {
            public void invoke(Object payload) {
                announcedMediaPlayer=payload;
            }
        });

        bus.sendPayload(UriString.from("ANY"))
                .withEvent(MediaPlaybackService.USER_LOAD_VIDEO);

        assertThat(fakeMediaPlayer, is(announcedMediaPlayer));
    }

    @Override
    public Object createMediaPlayerForUri(UriString uri) {
        fakeMediaPlayer = new Object();
        return fakeMediaPlayer;
    }

}
