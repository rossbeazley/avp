package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchScreenView;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoControlScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;

import java.util.HashMap;
import java.util.Map;


public class DefaultFragmentFromScreen implements FragmentFromScreen {

    private Map<Class<? extends Screen>, Class<? extends Fragment>> fragmentsByScreen;

    public DefaultFragmentFromScreen() {
        fragmentsByScreen = new HashMap<Class<? extends Screen>, Class<? extends Fragment>>(){{
            put(SearchScreenView.class, SearchFragment.class);
            put(VideoControlScreen.class, VideoPlayerFragment.class);
        }};
    }

    public Class<? extends Fragment> fragmentClass(Class<? extends Screen> screen) {
        return fragmentsByScreen.get(screen);
    }
}
