package uk.co.rossbeazley.redux.android.navigation;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;
import uk.co.rossbeazley.redux.android.ui.videoplayer.VideoPlayerFragment;

public class FragmentTransactionNavigationController implements NavigationController {

    private FragmentManager fm;

    public FragmentTransactionNavigationController() {
        fm = null;
    }

    public void attachFragmentManager(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void showVideoPlayScreen() {
        Fragment fragment = new VideoPlayerFragment();
        showFragment(fragment);
    }

    public void showFragment(Fragment fragment) {
        if (fm != null) {
            fm.beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }

}