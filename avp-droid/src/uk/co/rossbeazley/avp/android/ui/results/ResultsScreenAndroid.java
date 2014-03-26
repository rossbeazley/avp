package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

class ResultsScreenAndroid implements ResultsScreen {
    private View searchSpinner;

    public ResultsScreenAndroid(CanFindViewById canFindViewById) {
        this.searchSpinner = canFindViewById.findViewById(R.id.searchspinner);
    }

    @Override
    public void showSpinner() {
        searchSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults(Results results) {
        //TODO implement the show results method
    }

    @Override
    public void tearDown() {
        searchSpinner = null;
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
