package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchFragmentScreenFactory implements FragmentScreenFactory {
    private EventBus bus;

    public SearchFragmentScreenFactory(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        SearchScreenAndroidView result = new SearchScreenAndroidView(inflatedLayoutView);
        new SearchScreenPresenter(result, bus);
        return result;
    }
}
