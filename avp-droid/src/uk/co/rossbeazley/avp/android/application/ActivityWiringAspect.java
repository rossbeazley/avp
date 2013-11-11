package uk.co.rossbeazley.avp.android.application;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import uk.co.rossbeazley.avp.android.ApplicationServices;
import uk.co.rossbeazley.avp.android.activity.WireableActivity;
import uk.co.rossbeazley.avp.android.log.Logger;
import uk.co.rossbeazley.avp.android.navigation.FragmentTransactionNavigationController;
import uk.co.rossbeazley.avp.android.navigation.NavigationController;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;

public class ActivityWiringAspect extends EmptyActivityLifecycleCallbacks {

    private final ApplicationServices applicationServices;
    private ActivityWirer activityWirer;
    private Logger logger;

    ActivityWiringAspect(ApplicationServices applicationServices, Logger logger) {
        this.applicationServices = applicationServices;
        this.logger = logger;
        activityWirer = new ActivityWirer(applicationServices);

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        logger.debug("Wiring " + activity.getLocalClassName());
        if(activity instanceof WireableActivity) {
            ((WireableActivity)activity).wire(activityWirer);
        }

        constructNavigationController(activity);

    }

    private void constructNavigationController(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransactionNavigationController fragmentTransactionNavigationController = new FragmentTransactionNavigationController(fragmentManager);

        final NavigationController navigationController = fragmentTransactionNavigationController;

        //TODO this maybe should be its own object that routes event bus events to method calls
        EventBus bus = applicationServices.getBus();
        bus.whenEvent("load_video")
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        navigationController.showVideoPlayScreen();
                    }
                });
    }

}
