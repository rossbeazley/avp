package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.search.Results;

class FakeResultsScreen implements ResultsScreen {
    public Results actualResults;
    public static final String SHOWN = "visible";
    public static final String HIDDEN = "hidden";
    public String spinner = SHOWN;
    public ResultsScreen.CanListenForResultSelection listener;

    FakeResultsScreen() {
    }

    public FakeResultsScreen(Results results) {
        this.actualResults = results;
    }

    @Override
    public void showResults(Results results) {
        actualResults = results;
    }

    @Override
    public void showSpinner() {
        spinner = SHOWN;
    }

    @Override
    public void hideSpinner() {
        spinner = HIDDEN;
    }

    @Override
    public void tearDown() {
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
    }

    @Override
    public void setResultSelectedListener(CanListenForResultSelection listener) {
        this.listener = listener;
    }

}
