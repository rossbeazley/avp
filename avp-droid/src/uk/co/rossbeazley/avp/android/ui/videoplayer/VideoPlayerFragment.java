package uk.co.rossbeazley.avp.android.ui.videoplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.FragmentLayoutInflater;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;

/**
 * Created with IntelliJ IDEA.
 * User: rdlb
 * Date: 13/09/13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class VideoPlayerFragment extends Fragment {

    public void setVideoPlayerFragmentScreenFactory(FragmentScreenFactory fragmentScreenFactory) {
        this.fragmentScreenFactory = fragmentScreenFactory;
    }

    private FragmentScreenFactory fragmentScreenFactory;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLayoutInflater screenInflater = fragmentScreenFactory.createScreenFromLayoutInflatorAndViewGroup(inflater, container);
        return screenInflater.getInflatedView();
    }

    public View onCreateView2(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutResID = R.layout.videoplayer;
        View inflatedView = inflater.inflate(layoutResID, container, false);
        fragmentScreenFactory.createScreenFromLayoutInflatorAndViewGroup(inflater, container);
        return inflatedView;
    }

}
