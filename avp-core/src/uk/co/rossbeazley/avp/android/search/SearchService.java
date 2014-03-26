package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchService implements CanDispatchSearchQuery {
    final private EventBus bus;
    final private MediaRepository mediaRepository;

    public SearchService(EventBus bus, MediaRepository mediaRepository) {
        this.bus = bus;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public void query(Query searchString) {
        Search search = Search.fromQuery(searchString);
        bus.sendPayload(search)
                .withEvent(Events.SEARCH_CREATED);
        mediaRepository.execute(search);
    }
}
