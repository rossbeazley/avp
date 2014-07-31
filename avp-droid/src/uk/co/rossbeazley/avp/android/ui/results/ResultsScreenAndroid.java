package uk.co.rossbeazley.avp.android.ui.results;

import android.view.View;
import android.widget.ListView;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

class ResultsScreenAndroid implements ResultsScreen {
    private final CanFindViewById canFindViewById;
    private View searchSpinner;
    private CanListenForResultSelection listener;

    public ResultsScreenAndroid(CanFindViewById canFindViewById) {
        this.canFindViewById = canFindViewById;
        this.searchSpinner = canFindViewById.findViewById(R.id.searchspinner);
        listener = CanListenForResultSelection.NULL;
    }

    @Override
    public void showSpinner() {
        searchSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults(Results results) {
        //TODO implement the show results method
        ListView listView = findListView(R.id.searchresultslist);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(new ResultsListAdapter(results));
    }

    @Override
    public void hideSpinner() {
        searchSpinner.setVisibility(View.GONE);
    }

    @Override
    public void setResultSelectedListener(CanListenForResultSelection listener) {
        this.listener = listener;
    }

    private ListView findListView(int id) {
        return ((ListView) canFindViewById.findViewById(id));
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
