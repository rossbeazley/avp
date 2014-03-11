package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ScreenResourceIdFragment extends Fragment implements NeedsAnInflatedViewFactory {

    private FragmentScreenFactory fragmentScreenFactory;
    private Screen screen;

    private InflatedViewFactory inflatedViewFactory;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InflatedView inflatedLayoutView = inflateLayoutResource(inflater, container);
        buildScreen(inflatedLayoutView);
        return inflatedLayoutView.inflatedView();
    }

    private void buildScreen(CanFindViewById inflatedLayoutView) {
        screen = fragmentScreenFactory.buildScreenWithInflatedView(inflatedLayoutView);
    }

    private InflatedView inflateLayoutResource(LayoutInflater inflater, ViewGroup container) {
        return inflatedViewFactory.createInflatedView(inflater, container, resourceId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        screen.tearDown();
        screen = null;
    }
    //TODO generify this
    public void injectFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    public void injectInflatedViewFactory(InflatedViewFactory inflatedViewFactory) {
        this.inflatedViewFactory = inflatedViewFactory;
    }

    protected abstract int resourceId();
}
