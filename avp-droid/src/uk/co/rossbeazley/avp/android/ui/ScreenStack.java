package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;

public interface ScreenStack {
    //TODO if we didnt use the fragments class but something from core, we could pull the interface into core
    //TODO    maybe we could use the Screen class as alookup for the fragment?
    void pushFragment(Class<? extends Fragment> fragmentClass);
}
