package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;

public class HomeFragment extends ScreenResourceIdFragment implements InjectableHomeFragment {
    @Override
    protected int resourceId() {
        return R.layout.home;
    }
}
