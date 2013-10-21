package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.activity.WireableMain;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class ActivityWirer {

    private final ApplicationServices applicationServices;

    public ActivityWirer(ApplicationServices applicationServices) {
        this.applicationServices = applicationServices;
    }

    public void wire(WireableMain activity) {
        EventBus bus = applicationServices.getBus();
        activity.setEventBus(bus);

        activity.setIntentParser(applicationServices.getIntentParser());

        activity.setLogger(applicationServices.getLogger());
    }

}
