package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.FragmentManager;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.search.SearchNavigationController;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerNavigationController;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class UiNavigationStackFactory {

    public void createNavigationViewControllers(FragmentManager fragmentManager, EventBus eventbus) {

        ScreenStack screenStack = createScreenBackStack(fragmentManager);
        new VideoPlayerNavigationController(screenStack, eventbus);
        new SearchNavigationController(screenStack, eventbus);
    }

    private ScreenStack createScreenBackStack(FragmentManager fragmentManager) {
        FragmentFromScreen fragmentFromScreen = new DefaultFragmentFromScreen();
        FragmentManagerTransaction fragmentTransaction = new FragmentManagerTransaction(fragmentManager);
        return new ScreenFragmentStack(fragmentFromScreen, fragmentTransaction);
    }
}
