package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public class Search implements CurrentSearchResults, CurrentResult {

    private final MediaRepository repo;
    private final EventBus bus;
    private Results results;
    private SearchState state;

    private MediaItem selectResult;

    public Search(final MediaRepository repo, final EventBus bus) {
        this.repo = repo;
        this.bus = bus;
        state = new NoResults();
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
        changeState(new ResultsAvailable(results, bus));
    }

    private void changeState(ResultsAvailable newState) {
        state = newState;
        state.announce();
    }

    public void announceState() {
        state.announce();
    }

    @Override
    public void selectResult(MediaItem selected) {
        this.selectResult = selected;
        bus.sendPayload(selected).withEvent(MEDIA_ITEM_AVAILABLE);
    }

    private interface SearchState {
        void announce();
    }

    private class NoResults implements SearchState {

        @Override
        public void announce() {
            bus.announce(NO_SEARCH_RESULTS_AVAILABLE);
        }
    }

    private class ResultsAvailable implements SearchState {
        private final Results results;
        private final EventBus bus;

        public ResultsAvailable(Results results, EventBus bus) {
            this.results = results;
            this.bus = bus;
        }
        @Override
        public void announce() {
            bus.sendPayload(results)
                    .withEvent(SEARCH_RESULTS_AVAILABLE);
        }
    }
}
