package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflatedView implements CanFindViewById {

    private final View inflatedView;
    private final LayoutInflater inflater;
    private final ViewGroup container;

    public InflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        this.inflater = inflater;
        this.container = container;
        this.inflatedView = inflateLayout(layoutId);
    }

    private View inflateLayout(int layoutResID) {
         return inflater.inflate(layoutResID,container,false);
    }

    public View androidView() {
        return inflatedView;
    }

    @Override
    public View findViewById(int id) {
        return inflatedView.findViewById(id);
    }
}