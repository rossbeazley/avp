package uk.co.rossbeazley.avp.android.application;

import android.content.Intent;
import android.net.Uri;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class IntentToEventDispatcher {

    private EventBus bus;
    private final Logger logger;

    public IntentToEventDispatcher(EventBus bus, Logger logger) {
        this.bus = bus;
        this.logger = logger;
    }

    public void onIntent(Intent intent) {

//        UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
//        bus.sendPayload(uriString).withEvent(Events.USER_LOAD_VIDEO);
        bus.announce(Events.APP_START);

        logger.debug("onNewIntent !! " + dataStringFromIntent(intent));
    }


    private String dataStringFromIntent(Intent intent) {
        String dataString = "NONE'";
        if (intent!=null) {
            Uri data = intent.getData();

            if(data!=null) {
                dataString = data.toString();
            }
        }
        return dataString;
    }

}
