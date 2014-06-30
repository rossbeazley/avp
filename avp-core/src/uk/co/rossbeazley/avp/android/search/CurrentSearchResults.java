package uk.co.rossbeazley.avp.android.search;

import uk.co.rossbeazley.avp.android.media.MediaItem;

public interface CurrentSearchResults {
    String NO_SEARCH_RESULTS_AVAILABLE = "no_search_results";
    String SEARCH_RESULTS_AVAILABLE = "search_completed";

    void announceState();


}
