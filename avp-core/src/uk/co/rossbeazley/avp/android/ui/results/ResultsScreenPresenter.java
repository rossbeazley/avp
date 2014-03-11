package uk.co.rossbeazley.avp.android.ui.results;


class ResultsScreenPresenter {
    public ResultsScreenPresenter(ResultsScreen screen) {
        defaultEvent(screen);
    }

    private void defaultEvent(ResultsScreen screen) {
        screen.showSpinner();
    }
}
