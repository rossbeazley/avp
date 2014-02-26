package uk.co.rossbeazley.avp.android.ui;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentManagerTransaction implements FragmentTransaction {
    final FragmentManager fm;

    public FragmentManagerTransaction(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void addFragmentToBackStack(Fragment fragment) {
        fm.beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }
}