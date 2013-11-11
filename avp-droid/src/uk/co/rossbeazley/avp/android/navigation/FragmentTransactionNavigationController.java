package uk.co.rossbeazley.avp.android.navigation;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;

public class FragmentTransactionNavigationController implements NavigationController {

    final private FragmentManager fm;

    public FragmentTransactionNavigationController(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void showVideoPlayScreen() {
        Fragment fragment = new VideoPlayerFragment();
        showFragment(fragment);
    }

    public void showFragment(Fragment fragment) {
            fm.beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
    }

}