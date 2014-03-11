package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.SearchService;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchFragmentScreenFactory implements FragmentScreenFactory {
    private final EventBus bus;

    public SearchFragmentScreenFactory(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        SearchScreenAndroid result = new SearchScreenAndroid(inflatedLayoutView);
        createPresenter(result);
        return result;
    }

    private void createPresenter(SearchScreenAndroid result) {
        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus); //TODO just newing the edge of the app here, dosnt feel quite right
        new SearchScreenPresenter(result, canDispatchSearchQuery);
    }
}
