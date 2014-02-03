package uk.co.rossbeazley.avp.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflatedLayoutView implements InflatedView {

    private final View inflatedView;

    public InflatedLayoutView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        this.inflatedView = inflater.inflate(layoutId, container,false);
    }

    @Override
    public View inflatedView() {
        return inflatedView;
    }

    @Override
    public View findViewById(int id) {
        return inflatedView.findViewById(id);
    }
}
