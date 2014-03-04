package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchService implements CanDispatchSearchQuery {
    private EventBus bus;

    public SearchService(EventBus bus) {
        this.bus = bus;
    }

    @Override         //TODO, introduce search query object, infact i think this class should make one and tell the world about it
    public void query(String searchString) {
        //UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
        UriString uriString = new UriString(searchString);
        bus .sendPayload(uriString)
            .withEvent(Events.USER_LOAD_VIDEO);
    }
}
