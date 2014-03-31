package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class Search {

    private final MediaRepository repo;
    private final EventBus bus;

    public Search(final MediaRepository repo, final EventBus bus) {
        this.repo = repo;
        this.bus = bus;
        bindToUserQueryEvent(bus);

    }

    private void bindToUserQueryEvent(EventBus bus) {
        bus.whenEvent(Events.USER_QUERY)
                .thenRun(new FunctionWithParameter<Query>() {
                    @Override
                    public void invoke(Query payload) {
                        executeQuery(payload);

                    }
                });
    }

    private void executeQuery(Query payload) {
        Results results = repo.execute(payload);
        bus.sendPayload(results)
                .withEvent(Events.SEARCH_COMPLETED);
    }
}
