package uk.co.rossbeazley.avp.android.application;

import uk.co.rossbeazley.avp.android.ReduxApplicationServices;
import uk.co.rossbeazley.avp.android.activity.WireableMain;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class ActivityWirer {

    private final ReduxApplicationServices reduxApplicationServices;

    public ActivityWirer(ReduxApplicationServices reduxApplicationServices) {
        this.reduxApplicationServices = reduxApplicationServices;
    }

    public void wire(WireableMain activity) {
        EventBus bus = reduxApplicationServices.getBus();
        activity.setEventBus(bus);

        activity.setIntentParser(reduxApplicationServices.getIntentParser());

        activity.setLogger(reduxApplicationServices.getLogger());
    }

}
