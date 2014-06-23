package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.FragmentManager;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;

class EmptyFragmentBackStack {

    public EmptyFragmentBackStack(final FragmentManager fragmentManager, final EventBus eventBus) {

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(fragmentManager.getBackStackEntryCount()==0) {
                    eventBus.announce(Events.UI_CLOSED);
                }
            }
        });
    }
}
