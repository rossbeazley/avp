package uk.co.rossbeazley.redux.android.application;

import uk.co.rossbeazley.redux.android.ReduxApplicationServices;
import uk.co.rossbeazley.redux.android.activity.WireableMain;
import uk.co.rossbeazley.redux.eventbus.EventBus;

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
