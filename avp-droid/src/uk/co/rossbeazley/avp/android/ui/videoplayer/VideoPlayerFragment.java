package uk.co.rossbeazley.avp.android.ui.videoplayer;

import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;

public class VideoPlayerFragment extends ScreenResourceIdFragment implements InjectableVideoPlayerFragment {

    @Override
    protected int resourceId() {
        return R.layout.videoplayer;
    }

}
