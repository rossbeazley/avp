package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;

final class FragmentManagerTransaction implements FragmentTransaction {
    final FragmentManager fm;

    FragmentManagerTransaction(FragmentManager fm) {
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
