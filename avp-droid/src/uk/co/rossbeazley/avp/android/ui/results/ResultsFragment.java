package uk.co.rossbeazley.avp.android.ui.results;

import android.app.Fragment;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;
import uk.co.rossbeazley.avp.android.ui.search.InjectableSearchFragment;

public class ResultsFragment extends ScreenResourceIdFragment implements InjectableResultsFragment{
    @Override
    protected int resourceId() {
        return R.layout.results;
    }
}
