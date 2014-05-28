package uk.co.rossbeazley.avp.android.ui.results;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import uk.co.rossbeazley.avp.android.search.Results;

class ResultsListAdapter extends BaseAdapter {

    public static final int RESULT_VIEW_TYPE = 30035;
    private final Results results;

    public ResultsListAdapter(Results results) {
        this.results = results;
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

        /**
         * use an inflated layout,
         * custom class in the layout with sub views
         * custom class has the necessary methods to set the data without violating encapsulation
         * onFinishInflate you can grab instances of your sub views
         */

        return new ResultsListItemView(parentViewGroup.getContext());
    }

    @Override
    public int getItemViewType(int i) {
        return RESULT_VIEW_TYPE;
    }

    @Override
    public boolean isEmpty() {
        return results.empty();
    }

    private class ResultsListItemView extends View {
        public ResultsListItemView(Context context) {
            super(context);
        }

        public ResultsListItemView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ResultsListItemView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
    }
}
