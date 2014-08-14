package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;

public final class SearchFragment extends ScreenResourceIdFragment implements InjectableSearchFragment {
    @Override
    protected int resourceId() {
        return R.layout.search;
    }
}
