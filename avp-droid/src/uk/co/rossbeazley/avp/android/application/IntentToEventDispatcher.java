package uk.co.rossbeazley.avp.android.application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public final class IntentToEventDispatcher {

    private EventBus bus;
    private final Logger logger;

    public IntentToEventDispatcher(EventBus bus, Logger logger) {
        this.bus = bus;
        this.logger = logger;
    }

    public void onIntent(Intent intent) {

//        UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
//        bus.sendPayload(uriString).withEvent(Events.USER_LOAD_VIDEO);
        bus.announce(ApplicationCore.APP_START);

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

    public void onIntent(Intent intent, Bundle savedInstanceState) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
