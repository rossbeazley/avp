package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchService implements CanDispatchSearchQuery {
    private EventBus bus;

    public SearchService(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void query(String searchString) {

    }
}
