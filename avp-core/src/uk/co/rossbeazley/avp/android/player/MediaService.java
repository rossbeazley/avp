package uk.co.rossbeazley.avp.android.player;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class MediaService implements CanPlayMedia {
    private EventBus bus;

    public MediaService(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void play(String searchString) {
        //UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
        UriString uriString = UriString.from(searchString);
        bus .sendPayload(uriString)
            .withEvent(Events.USER_LOAD_VIDEO);
    }
}
