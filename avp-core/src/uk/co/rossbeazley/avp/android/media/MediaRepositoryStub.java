package uk.co.rossbeazley.avp.android.media;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import java.util.Map;

public class MediaRepositoryStub implements MediaRepository {
    private final EventBus bus;
    private final Map<Query, Results> resultsByQuery;

    public MediaRepositoryStub(EventBus bus, Map<Query, Results> resultsByQuery) {
        this.bus = bus;
        this.resultsByQuery = resultsByQuery;
    }

    @Override
    public void execute(Search search) {
        Results results = resultsByQuery.get(search.query());
        search.results(results);
        bus.sendPayload(search)
                .withEvent(Events.SEARCH_COMPLETED);
    }
}
