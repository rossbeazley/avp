package uk.co.rossbeazley.avp.android.player.ui;

import org.robolectric.Robolectric;
import uk.co.rossbeazley.avp.android.ActivityForTestingViews;

public class ActivityTestSupport {
    public static ActivityForTestingViews createVisibleActivity() {
        return Robolectric.buildActivity(ActivityForTestingViews.class).create().visible().get();
    }
}
