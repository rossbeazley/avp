package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenResourceIdFragment;
import uk.co.rossbeazley.avp.android.ui.playout.PlayoutFragment;
import uk.co.rossbeazley.avp.android.ui.results.ResultsFragment;
import uk.co.rossbeazley.avp.android.ui.results.ResultsScreen;
import uk.co.rossbeazley.avp.android.ui.search.SearchFragment;
import uk.co.rossbeazley.avp.android.ui.search.SearchScreen;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreen;
import uk.co.rossbeazley.avp.android.ui.urloader.UrlLoaderFragment;
import uk.co.rossbeazley.avp.android.ui.videoplayer.MediaPlayerScreen;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;

import java.util.HashMap;
import java.util.Map;


final class DefaultFragmentFromScreen implements FragmentFromScreen {

    private Map<Class<? extends Screen>, Class<? extends Fragment>> fragmentsByScreen;

    DefaultFragmentFromScreen() {
        fragmentsByScreen = new HashMap<Class<? extends Screen>, Class<? extends Fragment>>(){{
            put(SearchScreen.class, SearchFragment.class);
            put(MediaPlayerScreen.class, PlayoutFragment.class);
//            put(MediaPlayerScreen.class, VideoPlayerFragment.class);
            put(UrlLoaderScreen.class, UrlLoaderFragment.class);
            put(ResultsScreen.class, ResultsFragment.class);
        }};
    }

    public Class<? extends Fragment> fragmentClass(Class<? extends Screen> screen) {
        return fragmentsByScreen.get(screen);
    }
}
