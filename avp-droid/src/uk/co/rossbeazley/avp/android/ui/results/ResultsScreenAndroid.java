package uk.co.rossbeazley.avp.android.ui.results;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;

class ResultsScreenAndroid implements ResultsScreen {
    private final ListView searchResultsList;
    private View searchSpinner;

    public ResultsScreenAndroid(CanFindViewById canFindViewById) {
        this.searchSpinner = canFindViewById.findViewById(R.id.searchspinner);
        this.searchResultsList = (ListView) canFindViewById.findViewById(R.id.searchresultslist);
    }

    @Override
    public void showSpinner() {
        searchSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults(Results results) {
        //TODO implement the show results method
        searchResultsList.setAdapter(new ResultsListAdapter());
    }

    @Override
    public void tearDown() {
        searchSpinner = null;
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private static class ResultsListAdapter implements ListAdapter {
        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int i) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
