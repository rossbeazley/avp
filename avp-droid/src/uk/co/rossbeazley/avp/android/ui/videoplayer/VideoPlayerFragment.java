package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.InflatedView;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;

public class VideoPlayerFragment<T extends VideoPlayerFragmentScreenFactory> extends Fragment {

    private FragmentScreenFactory fragmentScreenFactory;
    public void setVideoPlayerFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }


    @Override     // a generic method that could maybe be pulled into a superclass?
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        InflatedView screenInflater = getFragmentScreenFactory().buildScreenFromLayoutInflatorAndViewGroup(inflater, container);
        return screenInflater.androidView();
    }

    private FragmentScreenFactory getFragmentScreenFactory() {
        return fragmentScreenFactory;
    }


}
