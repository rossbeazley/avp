package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class Search implements CurrentSearchResults {

    private final MediaRepository repo;
    private final EventBus bus;
    private Results results;
    private Search.ResultsAvailable state;

    public Search(final MediaRepository repo, final EventBus bus) {
        this.repo = repo;
        this.bus = bus;
        bindToUserQueryEvent(bus);

    }

    private void bindToUserQueryEvent(EventBus bus) {
        bus.whenEvent(Events.PERFORMING_QUERY)
                .thenRun(new FunctionWithParameter<Query>() {
                    @Override
                    public void invoke(Query payload) {
                        executeQuery(payload);

                    }
                });
    }

    private void executeQuery(Query payload) {
        results = repo.execute(payload);
        state = new ResultsAvailable(results, bus);
        state.announce();
    }

    public void announceState() {
        state.announce();
    }

    private class ResultsAvailable {
        private final Results results;
        private final EventBus bus;

        public ResultsAvailable(Results results, EventBus bus) {
            this.results = results;
            this.bus = bus;
        }

        public void announce() {
            bus.sendPayload(results)
                    .withEvent(Events.SEARCH_RESULTS_AVAILABLE);
        }
    }
}
