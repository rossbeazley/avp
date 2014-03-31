package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.media.Programme;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.SearchService;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

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
        Map<Query, Results> resultsByQuery;
        resultsByQuery = new HashMap<Query, Results>(1){{
            put(Query.fromString("search"), new Results(new Programme()));
        }};
        MediaRepository mediaRepository = new MediaRepositoryStub(resultsByQuery);
        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus); //TODO just newing the edge of the app here, dosnt feel quite right
        new SearchScreenPresenter(result, canDispatchSearchQuery);
    }
}
