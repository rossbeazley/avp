package uk.co.rossbeazley.redux.android;

import android.app.Activity;
import android.view.View;
import uk.co.rossbeazley.redux.android.ui.CanFindViewById;
import uk.co.rossbeazley.redux.android.ui.CanInflateLayout;

/**
* Created with IntelliJ IDEA.
* User: rdlb
* Date: 07/09/13
* Time: 21:48
* To change this template use File | Settings | File Templates.
*/
public class ActivityForTestingViews extends Activity  {

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

}
