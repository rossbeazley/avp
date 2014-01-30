package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ScreenResourceIdFragment extends Fragment {

    private FragmentScreenFactory fragmentScreenFactory;
    private Screen screen;

    private InflatedViewFactory inflatedViewFactory;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InflatedView inflatedView = inflateLayoutResource(inflater, container);
        buildScreen(inflatedView);
        return inflatedView.androidView();
    }

    private void buildScreen(InflatedView inflatedView) {
        screen = fragmentScreenFactory.buildScreenWithInflatedView(inflatedView);
    }

    private InflatedView inflateLayoutResource(LayoutInflater inflater, ViewGroup container) {
        inflatedViewFactory = new DefaultInflatedViewFactory();
        return inflatedViewFactory.createInflatedView(inflater, container, resourceId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        screen.tearDown();
    }

    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    public void setInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }

    protected abstract int resourceId();
}
