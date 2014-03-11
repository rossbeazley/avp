package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface InflatedViewFactory {
    InflatedView createInflatedView(LayoutInflater inflater, ViewGroup container, int layoutId);
}
