package uk.co.rossbeazley.avp.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class VideoPlayerCreatorEventDispatcherTest {

    private final UriString expectedUri = UriString.from("ANY_URI");
    private UriString createdWithUri = UriString.from("NONE");

    @Test
    public void eventBusAnnouncmentCausesMediaPlayerCreation() {
        EventBus bus = new ExecutorEventBus();
        MediaPlayerCreator videoCreator = new MediaPlayerCreator() {
            public void create(UriString any_uri_string) {
                createdWithUri = any_uri_string;
            }

            public void addCreatedListener(CreatedListener createdListener) {
            }
        };

        new VideoPlayerCreatorEventDispatcher(bus, videoCreator);
        bus.sendPayload(expectedUri).withEvent(Events.LOAD_VIDEO);

        assertThat(createdWithUri,is(expectedUri));


        fail("need to refactor more");
    }
}
