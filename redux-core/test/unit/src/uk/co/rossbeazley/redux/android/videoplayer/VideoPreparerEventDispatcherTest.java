package uk.co.rossbeazley.redux.android.videoplayer;

import org.junit.Test;
import uk.co.rossbeazley.redux.Events;
import uk.co.rossbeazley.redux.UriString;
import uk.co.rossbeazley.redux.eventbus.EventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 25/09/13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class VideoPreparerEventDispatcherTest implements VideoPreparer {

    private UriString urlLoaded = new UriString(""){ public String toString(){ return "NO URI";}};

    @Test
    public void videoDecoderLoadsUriOnLoadVideoEvent() {
        EventBus bus = anEventBus();
        VideoPreparerEventDispatcher decoder = new VideoPreparerEventDispatcher(bus, this);

        UriString ANY_URL = new UriString("any_uri"){public String toString(){ return "AN URI";}};

        bus.sendPayload(ANY_URL).withEvent(Events.LOAD_VIDEO);

        assertThat(urlLoaded, is(ANY_URL));
    }

    private EventBus anEventBus() {
        return uk.co.rossbeazley.redux.eventbus.EventBusFactory.createEventBus();
    }

    @Override
    public void loadVideoUrl(UriString url) {
        urlLoaded = url;
    }
}
