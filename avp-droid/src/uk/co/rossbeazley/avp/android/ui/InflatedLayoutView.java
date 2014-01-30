package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflatedLayoutView implements CanFindViewById {

    private final View inflatedView;

    public InflatedLayoutView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        this.inflatedView = inflater.inflate(layoutId, container);
    }

    @Override
    public View findViewById(int id) {
        return inflatedView.findViewById(id);
    }
}
