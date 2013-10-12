package uk.co.rossbeazley.redux.android.application;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import uk.co.rossbeazley.redux.android.ReduxApplicationServices;
import uk.co.rossbeazley.redux.android.activity.WireableActivity;
import uk.co.rossbeazley.redux.android.log.Logger;
import uk.co.rossbeazley.redux.android.navigation.FragmentTransactionNavigationController;
import uk.co.rossbeazley.redux.android.navigation.NavigationController;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.Function;

public class ActivityWiringAspect extends EmptyActivityLifecycleCallbacks {

    private final ReduxApplicationServices reduxApplicationServices;
    private ActivityWirer activityWirer;
    private Logger logger;

    ActivityWiringAspect(ReduxApplicationServices reduxApplicationServices, Logger logger) {
        this.reduxApplicationServices = reduxApplicationServices;
        this.logger = logger;
        activityWirer = new ActivityWirer(reduxApplicationServices);

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
        FragmentTransactionNavigationController fragmentTransactionNavigationController = new FragmentTransactionNavigationController();
        fragmentTransactionNavigationController.attachFragmentManager(fragmentManager);

        final NavigationController navigationController = fragmentTransactionNavigationController;

        //TODO this maybe should be its own object that routes event bus events to method calls
        EventBus bus = reduxApplicationServices.getBus();
        bus.whenEvent("load_video")
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        navigationController.showVideoPlayScreen();
                    }
                });
    }

}
