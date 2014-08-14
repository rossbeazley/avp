package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;
import uk.co.rossbeazley.avp.android.ui.Screen;

//TODO can I remove this final class ??
interface FragmentFromScreen {
    Class<? extends Fragment> fragmentClass(Class<? extends Screen> screen);
}
