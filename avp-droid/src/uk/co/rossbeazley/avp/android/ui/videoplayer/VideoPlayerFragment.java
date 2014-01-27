package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.InflatedView;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;

public class VideoPlayerFragment extends Fragment {

    private FragmentScreenFactory fragmentScreenFactory;
    private Screen screen;

    public void setVideoPlayerFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }


    @Override     // a generic method that could maybe be pulled into a superclass?
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        InflatedView inflatedView = inflateLayoutResource(inflater, container);
        buildScreen(inflatedView);
        return inflatedView.androidView();
    }

    private void buildScreen(InflatedView inflatedView) {
        screen = getFragmentScreenFactory().buildScreenWithInflatedView(inflatedView);
    }

    private InflatedView inflateLayoutResource(LayoutInflater inflater, ViewGroup container) {
        return new InflatedView(inflater, container, resourceId());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private int resourceId() {
        return R.layout.videoplayer;
    }

    private FragmentScreenFactory getFragmentScreenFactory() {
        return fragmentScreenFactory;
    }


}
