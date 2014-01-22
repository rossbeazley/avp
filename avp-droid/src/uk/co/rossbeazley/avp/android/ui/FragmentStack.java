package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;

public interface FragmentStack {
    void pushFragment(Class<? extends Fragment> fragmentClass);
}
