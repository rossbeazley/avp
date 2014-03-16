package uk.co.rossbeazley.avp.android.ui.results;


import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

class ResultsScreenPresenter {
    public ResultsScreenPresenter(final ResultsScreen screen, EventBus bus) {
        defaultEvent(screen);
        bus.whenEvent(Events.SEARCH_COMPLETED)
                .thenRun(new FunctionWithParameter<Search>() {
                    @Override
                    public void invoke(Search payload) {
                        screen.showResults(payload.results());
                    }
                });
    }

    private void defaultEvent(ResultsScreen screen) {
        screen.showSpinner();
    }
}
