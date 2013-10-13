package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.Events;
import uk.co.rossbeazley.redux.UriString;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VideoPreparerEventDispatcherTest implements VideoPreparer {

    private UriString urlLoaded = new UriString("NO URI");

    @Test
    public void videoDecoderLoadsUriOnLoadVideoEvent() {
        EventBus bus = anEventBus();
        VideoPreparerEventDispatcher decoder = new VideoPreparerEventDispatcher(bus, this);

        UriString ANY_URL = new UriString("ANY URI");

        bus.sendPayload(ANY_URL).withEvent(Events.LOAD_VIDEO);

        assertThat(urlLoaded, is(ANY_URL));
    }

    private EventBus anEventBus() {
        return new ExecutorEventBus();
    }

    @Override
    public void playVideoUrl(UriString url) {
        urlLoaded = url;
    }

    @Override
    public void addVideoLoadedListener(VideoLoadedListener videoLoadedListener) {
    }
}
