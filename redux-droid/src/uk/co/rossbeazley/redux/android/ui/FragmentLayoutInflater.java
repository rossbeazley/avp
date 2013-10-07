package uk.co.rossbeazley.redux.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
* Created with IntelliJ IDEA.
* User: rdlb
* Date: 13/09/13
* Time: 22:25
* To change this template use File | Settings | File Templates.
*/
public class FragmentLayoutInflater implements CanInflateLayout, CanFindViewById {

    private View inflatedView;
    private final LayoutInflater inflater;
    private final ViewGroup container;

    public FragmentLayoutInflater(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        this.container = container;
    }

    @Override
    public void inflateLayout(int layoutResID) {
        inflatedView = inflater.inflate(layoutResID,container,false);
    }

    public View getInflatedView() {
        return inflatedView;
    }

    @Override
    public View findViewById(int id) {
        return inflatedView.findViewById(id);
    }
}
