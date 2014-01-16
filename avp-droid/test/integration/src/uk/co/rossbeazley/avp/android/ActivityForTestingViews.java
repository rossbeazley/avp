package uk.co.rossbeazley.avp.android;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.CanInflateLayout;

/**
* Created with IntelliJ IDEA.
* User: rdlb
* Date: 07/09/13
* Time: 21:48
* To change this template use File | Settings | File Templates.
*/
public class ActivityForTestingViews extends Activity  {

    public Fragment lastFragmentAttached;

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
