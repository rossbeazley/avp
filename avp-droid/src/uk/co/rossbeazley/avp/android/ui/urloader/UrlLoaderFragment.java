package uk.co.rossbeazley.avp.android.ui.urloader;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;

public final class UrlLoaderFragment extends ScreenResourceIdFragment implements InjectableUrlLoaderFragment {
    @Override
    protected int resourceId() {
        return R.layout.urlloader;
    }
}
