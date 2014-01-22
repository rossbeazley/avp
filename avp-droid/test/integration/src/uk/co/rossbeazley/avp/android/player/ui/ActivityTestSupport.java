package uk.co.rossbeazley.avp.android.player.ui;

import org.robolectric.Robolectric;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;
import uk.co.rossbeazley.avp.android.R;

public class ActivityTestSupport {
    public static ActivityForTestingViews createVisibleActivity() {
        return Robolectric.buildActivity(ActivityForTestingViews.class).create().visible().get();
    }

    public static ActivityForTestingViews createVisibleActivityForLayout(int resourceId) {
        ActivityForTestingViews activity = createVisibleActivity();
        activity.setContentView(resourceId);
        return activity;
    }
}
