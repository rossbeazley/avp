package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchService implements CanDispatchSearchQuery {
    private EventBus bus;

    public SearchService(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void query(Query searchString) {
        Search search = Search.fromQuery(searchString);
        bus.sendPayload(search)
                .withEvent(Events.SEARCH_CREATED);
    }
}
