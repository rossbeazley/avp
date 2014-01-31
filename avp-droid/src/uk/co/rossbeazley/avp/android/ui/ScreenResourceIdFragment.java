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
        CanFindViewById inflatedLayoutView = inflateLayoutResource(inflater, container);
        buildScreen(inflatedLayoutView);
        return null; //inflatedLayoutView.androidView();
    }

    private void buildScreen(CanFindViewById inflatedLayoutView) {
        screen = fragmentScreenFactory.buildScreenWithInflatedView(inflatedLayoutView);
    }

    private CanFindViewById inflateLayoutResource(LayoutInflater inflater, ViewGroup container) {
        return inflatedViewFactory.createInflatedView(inflater, container, resourceId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        screen.tearDown();
        screen = null;
    }

    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    public void setInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }

    protected abstract int resourceId();
}
