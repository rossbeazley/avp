package uk.co.rossbeazley.avp.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.mediaplayer.MediaPlayer;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoPlayerCreatorEventAnnouncementTest {

    MediaPlayerCreator.CreatedListener registeredCreatedListener;

    MediaPlayer mediaPlayer = null;

    @Test
    public void mediaPlayerCreationCausesEventBusAnnouncement() {
        EventBus bus = new ExecutorEventBus();

        MediaPlayerCreator videoCreator = new MediaPlayerCreator() {
            public void create(UriString any_uri_string) {}

            public void addCreatedListener(CreatedListener createdListener) {
                registeredCreatedListener = createdListener;
            }
        };

        bus.whenEvent(Events.MEDIA_PLAYER_CREATED).thenRun(new FunctionWithParameter<MediaPlayer>() {
            public void invoke(MediaPlayer payload) {
                mediaPlayer=payload;
            }
        });

        new VideoPlayerCreatorEventDispatcher(bus, videoCreator);
        MediaPlayer expectedMediaPlayer = FakeMediaPlayer.createFakeMediaPlayer();
        registeredCreatedListener.created(expectedMediaPlayer);
        assertThat(mediaPlayer, is(expectedMediaPlayer));
    }
}
