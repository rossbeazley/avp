package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class ResultsScreenPresenter {
    public ResultsScreenPresenter(final ResultsScreen screen, EventBus bus) {
        defaultEvent(screen);
        bindToSearchCompletedEvent(screen, bus);
    }

    private void bindToSearchCompletedEvent(final ResultsScreen screen, EventBus bus) {
        bus.whenEvent(Events.SEARCH_COMPLETED)
                .thenRun(new FunctionWithParameter<Results>() {
                    @Override
                    public void invoke(Results payload) {
                        screen.showResults(payload);
                        screen.hideSpinner();
                    }
                });
    }

    private void defaultEvent(ResultsScreen screen) {
        screen.showSpinner();
    }
}
