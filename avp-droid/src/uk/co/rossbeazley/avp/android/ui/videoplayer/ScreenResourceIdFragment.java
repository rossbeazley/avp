package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.InflatedView;
import uk.co.rossbeazley.avp.android.ui.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 27/01/2014
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public abstract class ScreenResourceIdFragment extends Fragment {

    private FragmentScreenFactory fragmentScreenFactory;
    private Screen screen;

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
        return InflatedView.createInflatedView(inflater, container, resourceId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        screen.tearDown();
    }

    public void setFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    protected abstract int resourceId();
}
