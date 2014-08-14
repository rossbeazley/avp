package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public final class DefaultInflatedViewFactory implements InflatedViewFactory {
    @Override
    public InflatedView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        return new InflatedLayoutView(inflater, container, layoutId);
    }
}