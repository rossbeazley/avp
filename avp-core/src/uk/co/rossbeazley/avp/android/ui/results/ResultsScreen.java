package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.Screen;

public interface ResultsScreen extends Screen {
    void showSpinner();

    void showResults(Results results);

    void hideSpinner();

    void setResultSelectedListener(CanListenForResultSelection listener);

    public interface CanListenForResultSelection {
        void selected(MediaItem item);

        CanListenForResultSelection NULL = new CanListenForResultSelection() {public void selected(MediaItem item) {}};
    }
}
