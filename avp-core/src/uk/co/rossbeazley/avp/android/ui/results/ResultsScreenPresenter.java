package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.CurrentResult;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class ResultsScreenPresenter {
    public ResultsScreenPresenter(final ResultsScreen screen, final EventBus bus, final CurrentSearchResults currentSearch, CurrentResult currentResult) {
        bindToSearchCompletedEvent(screen, bus);
        bindToResultSelectedOnScreenEvent(screen, currentResult);
        currentSearch.announceState();
    }

    private void bindToSearchCompletedEvent(final ResultsScreen screen, EventBus bus) {
        bus.whenEvent(CurrentSearchResults.SEARCH_RESULTS_AVAILABLE)
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

    private void bindToResultSelectedOnScreenEvent(ResultsScreen screen, final CurrentResult currentResult) {
        screen.setResultSelectedListener(new ResultsScreen.CanListenForResultSelection() {
            @Override
            public void selected(MediaItem item) {
                currentResult.selectResult(item);
            }
        });
    }

}
