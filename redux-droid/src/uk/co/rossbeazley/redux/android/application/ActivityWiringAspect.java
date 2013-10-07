package uk.co.rossbeazley.redux.android.application;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.os.Bundle;
import uk.co.rossbeazley.redux.android.ReduxApplicationServices;
import uk.co.rossbeazley.redux.android.activity.WireableActivity;
import uk.co.rossbeazley.redux.android.activity.WireableMain;
import uk.co.rossbeazley.redux.android.navigation.FragmentTransactionNavigationController;
import uk.co.rossbeazley.redux.android.navigation.NavigationController;
import uk.co.rossbeazley.redux.eventbus.EventBus;
import uk.co.rossbeazley.redux.eventbus.Function;

public class ActivityWiringAspect implements Application.ActivityLifecycleCallbacks {

    private final ReduxApplicationServices reduxApplicationServices;
    private ActivityWirer activityWirer;

    ActivityWiringAspect(ReduxApplicationServices reduxApplicationServices) {
        this.reduxApplicationServices = reduxApplicationServices;
        activityWirer = new ActivityWirer(reduxApplicationServices);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        reduxApplicationServices.getLogger().debug("Wiring " + activity.getLocalClassName());
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

    public void onActivityStarted(Activity activity) {}
    public void onActivityResumed(Activity activity) {}
    public void onActivityPaused(Activity activity) {}
    public void onActivityStopped(Activity activity) {}
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
    public void onActivityDestroyed(Activity activity) {}

    public static class ActivityWirer {

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
}
