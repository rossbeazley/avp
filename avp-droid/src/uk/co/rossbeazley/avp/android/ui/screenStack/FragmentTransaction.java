package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;

interface FragmentTransaction {
    void addFragmentToBackStack(Fragment fragment);
}
