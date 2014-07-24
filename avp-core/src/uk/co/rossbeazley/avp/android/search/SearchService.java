package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchService implements CanDispatchSearchQuery {
    public static final String PERFORMING_QUERY = "search_created";
    final private EventBus bus;

    public SearchService(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void query(Query userQuery) {
        bus.sendPayload(userQuery)
                .withEvent(PERFORMING_QUERY);
    }

    /**
     * what if we had a currentQuery method, it returns the current query in the system?
     *
     * consider what happens when a query completes execution
     * but any observer that might be interested misses it
     */
}
