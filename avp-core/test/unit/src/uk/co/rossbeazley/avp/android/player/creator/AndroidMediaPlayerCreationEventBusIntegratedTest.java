package uk.co.rossbeazley.avp.android.player.creator;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.FakeMediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class AndroidMediaPlayerCreationEventBusIntegratedTest implements MediaPlayerFactory {
    private MediaPlayer fakeMediaPlayer;
    private MediaPlayer announcedMediaPlayer;

    @Test
    public void createsMediaPlayerAndTellsEveryone() {

        EventBus bus = new ExecutorEventBus();
        MediaPlayerCreator creator = new AndroidMediaPlayerCreator(this);

        new MediaPlayerCreatorEventDispatcher(bus, creator);

        bus.whenEvent(Events.MEDIA_PLAYER_CREATED).thenRun(new FunctionWithParameter<MediaPlayer>() {
            public void invoke(MediaPlayer payload) {
                announcedMediaPlayer=payload;
            }
        });


        fakeMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();

        bus.sendPayload(UriString.from("ANY"))
                .withEvent(Events.LOAD_VIDEO);

        assertThat(fakeMediaPlayer, is(announcedMediaPlayer));
    }

    @Override
    public MediaPlayer createMediaPlayerForUri(UriString uri) {
        return fakeMediaPlayer;
    }

}
