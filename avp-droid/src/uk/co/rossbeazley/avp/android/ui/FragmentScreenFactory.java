package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface FragmentScreenFactory {
    Screen buildScreenWithInflatedView(InflatedView inflatedView);
}
