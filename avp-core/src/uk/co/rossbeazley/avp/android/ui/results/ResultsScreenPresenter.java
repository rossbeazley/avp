package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class ResultsScreenPresenter {
    public ResultsScreenPresenter(final ResultsScreen screen, final EventBus bus, CurrentSearchResults currentSearch) {
        bindToSearchCompletedEvent(screen, bus);
        currentSearch.announceState();
    }

    private void bindToSearchCompletedEvent(final ResultsScreen screen, EventBus bus) {
        bus.whenEvent(Search.SEARCH_RESULTS_AVAILABLE)
                .thenRun(new FunctionWithParameter<Results>() {
                    @Override
                    public void invoke(Results payload) {
                        presentResults(payload, screen);
                    }
                });
    }

    private void presentResults(Results payload, ResultsScreen screen) {
        screen.showResults(payload);
        screen.hideSpinner();
    }

}
