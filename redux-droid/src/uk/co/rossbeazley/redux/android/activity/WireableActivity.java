package uk.co.rossbeazley.redux.android.activity;

import uk.co.rossbeazley.redux.android.application.ActivityWirer;

public interface WireableActivity {
    void wire(ActivityWirer activityWirer);
}
