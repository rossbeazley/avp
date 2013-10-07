package uk.co.rossbeazley.redux.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface FragmentScreenFactory {
    FragmentLayoutInflater createScreenFromLayoutInflatorAndViewGroup(LayoutInflater inflater, ViewGroup container);
}
