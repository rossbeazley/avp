package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 11/03/2014
* Time: 22:03
* To change this template use File | Settings | File Templates.
*/
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
    public void tearDown() {
        searchSpinner = null;
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
