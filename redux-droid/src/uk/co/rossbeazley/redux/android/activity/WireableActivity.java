package uk.co.rossbeazley.redux.android.activity;

import uk.co.rossbeazley.redux.android.application.ActivityWiringAspect;

public interface WireableActivity {
    void wire(ActivityWiringAspect.ActivityWirer activityWirer);
}
