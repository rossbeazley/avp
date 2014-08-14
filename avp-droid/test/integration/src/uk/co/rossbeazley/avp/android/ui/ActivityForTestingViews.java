package uk.co.rossbeazley.avp.android.ui;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import org.robolectric.Robolectric;

public final class ActivityForTestingViews extends Activity  {

    public Fragment lastFragmentAttached;

    public static ActivityForTestingViews createVisibleActivity() {
        return Robolectric.buildActivity(ActivityForTestingViews.class).create().visible().get();
    }

    public static ActivityForTestingViews createVisibleActivityForLayout(int resourceId) {
        ActivityForTestingViews activity = createVisibleActivity();
        activity.setContentView(resourceId);
        return activity;
    }

    public CanInflateLayout layoutInflater() {
        return new CanInflateLayout() {
            @Override
            public void inflateLayout(int layoutResID) {
                ActivityForTestingViews.this.setContentView(layoutResID);
            }
        };
    }

    public CanFindViewById viewFinder() {
        return new CanFindViewById() {
            @Override
            public View findViewById(int id) {
                return ActivityForTestingViews.this.findViewById(id);
            }
        };
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.lastFragmentAttached = fragment;
    }
}
