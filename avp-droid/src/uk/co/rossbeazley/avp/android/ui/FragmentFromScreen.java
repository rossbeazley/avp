package uk.co.rossbeazley.avp.android.ui;

import android.app.Fragment;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 19/02/2014
* Time: 12:54
* To change this template use File | Settings | File Templates.
*/
public interface FragmentFromScreen {
    Class<? extends Fragment> fragmentClass(Class<? extends Screen> screen);
}
