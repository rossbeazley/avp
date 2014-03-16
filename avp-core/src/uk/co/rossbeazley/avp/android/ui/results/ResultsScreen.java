package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.Screen;

public interface ResultsScreen extends Screen {
    void showSpinner();

    void showResults(Results results);
}
