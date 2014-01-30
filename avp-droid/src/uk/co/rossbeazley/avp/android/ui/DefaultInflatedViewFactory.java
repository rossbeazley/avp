package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DefaultInflatedViewFactory implements InflatedViewFactory {
    @Override
    public InflatedLayoutView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        return new InflatedLayoutView(inflater, container, layoutId);
    }
}