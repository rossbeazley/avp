package uk.co.rossbeazley.avp.android.ui.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.search.Results;

public class ResultsListAdapter extends BaseAdapter {

    public static final int RESULT_VIEW_TYPE = 30035;
    public static final boolean DO_NOT_ATTACH_TO_PARENT_VIEW = false;
    private final Results results;
    private final ResultsListAdapter.ResultsListItemViewFactory resultsListItemViewFactory;

    public ResultsListAdapter(Results results) {
        this.results = results;
        resultsListItemViewFactory = new ResultsListItemViewFactory();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public int getCount() {
        return results.count();
    }

    @Override
    public Object getItem(int i) {
        return results.result(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View viewToRecycle, ViewGroup parentViewGroup) {
        return resultsListItemViewFactory.create(viewToRecycle, parentViewGroup).forMediaItem(results.result(i));
    }

    @Override
    public int getItemViewType(int i) {
        return RESULT_VIEW_TYPE;
    }

    @Override
    public boolean isEmpty() {
        return results.empty();
    }





    private class ResultsListItemViewFactory {

        public ResultsListItemView create(View viewToRecycle, ViewGroup parentViewGroup) {

            return recycleableView(viewToRecycle) ? recycleView(viewToRecycle) : createView(parentViewGroup);
        }


        private boolean recycleableView(View viewToRecycle) {
            return viewToRecycle != null && viewToRecycle instanceof ResultsListItemView;
        }

        private ResultsListItemView recycleView(View viewToRecycle) {
            return (ResultsListItemView) viewToRecycle;
        }


        private ResultsListItemView createView(ViewGroup parentViewGroup) {
            ResultsListItemView result;
            LayoutInflater layoutInflator = layoutInflatorFromView(parentViewGroup);
            result = inflateResultsListItemView(parentViewGroup, layoutInflator);

            return result;
        }

        private LayoutInflater layoutInflatorFromView(ViewGroup parentViewGroup) {
            return (LayoutInflater) parentViewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        private ResultsListItemView inflateResultsListItemView(ViewGroup parentViewGroup, LayoutInflater layoutInflator) {
            return (ResultsListItemView) layoutInflator.inflate(R.layout.results_list_row, parentViewGroup, DO_NOT_ATTACH_TO_PARENT_VIEW);
        }

    }
}
