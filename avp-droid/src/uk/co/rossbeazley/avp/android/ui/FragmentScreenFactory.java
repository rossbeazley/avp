package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface FragmentScreenFactory {
    InflatedView buildScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container);
}
