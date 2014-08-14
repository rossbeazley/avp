package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.PayloadFunction;

public final class Search implements CurrentSearchResults {

    private final MediaRepository repo;
    private final EventBus bus;
    private SelectedMediaItem selectedMediaItem;
    private Results results;
    private SearchState state;



    public Search(final MediaRepository repo, final EventBus bus) {
        this.repo = repo;
        this.bus = bus;
        state = new NoResults();
        bindToUserQueryEvent(bus);
        registerAsProducer(bus);
        selectedMediaItem = new SelectedMediaItem(bus);
    }

    private void registerAsProducer(EventBus bus) {
        bus.registerProducer(SEARCH_RESULTS_AVAILABLE, new PayloadFunction<Results>() {
            @Override
            public void payload(FunctionWithParameter<Results> listener) {
                listener.invoke(results);   //TODO should only announce if there are results
            }
        });
    }

    private void bindToUserQueryEvent(EventBus bus) {
        bus.whenEvent(SearchService.PERFORMING_QUERY)
                .thenRun(new FunctionWithParameter<Query>() {
                    @Override
                    public void invoke(Query payload) {
                        executeQuery(payload);

                    }
                });
    }

    private void executeQuery(Query payload) {
        repo.execute(payload, new MediaRepository.Success() {
            @Override
            public void call(Results results) {
                Search.this.results = results;
                changeState(new ResultsAvailable(results, bus));
            }
        });

    }

    private void changeState(ResultsAvailable newState) {
        state = newState;
        state.announce();
    }

    public void announceState() {
        state.announce();
    }


    private interface SearchState {
        void announce();
    }

    private final class NoResults implements SearchState {

        @Override
        public void announce() {
            bus.announce(NO_SEARCH_RESULTS_AVAILABLE);
        }
    }

    private final class ResultsAvailable implements SearchState {
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
