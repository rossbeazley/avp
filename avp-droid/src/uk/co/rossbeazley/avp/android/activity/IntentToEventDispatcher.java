package uk.co.rossbeazley.avp.android.activity;

import android.content.Intent;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class IntentToEventDispatcher {

    private EventBus bus;

    public IntentToEventDispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void onIntent(Intent intent) {
        UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
        bus.sendPayload(uriString).withEvent(Events.LOAD_VIDEO);
    }
}
