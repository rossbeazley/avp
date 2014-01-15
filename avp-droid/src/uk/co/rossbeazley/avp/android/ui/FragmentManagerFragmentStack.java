package uk.co.rossbeazley.avp.android.ui;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentManagerFragmentStack implements FragmentStack {

    private final FragmentManager fm;

    public FragmentManagerFragmentStack(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void pushFragment(Fragment fragment) {
        fm.beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }
}
