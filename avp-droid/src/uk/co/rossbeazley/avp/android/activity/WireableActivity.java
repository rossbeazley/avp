package uk.co.rossbeazley.avp.android.activity;

import uk.co.rossbeazley.avp.android.application.ActivityWirer;

public interface WireableActivity {
    void wire(ActivityWirer activityWirer);
}
