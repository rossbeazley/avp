package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.FragmentManager;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class EmptyFragmentBackStack {

    public static final String UI_CLOSED = "ui_closed";

    public EmptyFragmentBackStack(final FragmentManager fragmentManager, final EventBus eventBus) {

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(fragmentManager.getBackStackEntryCount()==0) {
                    eventBus.announce(UI_CLOSED);
                }
            }
        });
    }
}
