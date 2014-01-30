package uk.co.rossbeazley.avp.android.ui.home;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.videoplayer.ScreenResourceIdFragment;

public class HomeFragment extends ScreenResourceIdFragment implements InjectableHomeFragment {
    @Override
    protected int resourceId() {
        return R.layout.home;
    }
}
