package uk.co.rossbeazley.avp;

import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.CurrentResult;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.android.search.SearchService;
import uk.co.rossbeazley.avp.android.search.SelectedMediaItem;
import uk.co.rossbeazley.avp.eventbus.EventBus;

/**
 * Created with IntelliJ IDEA.
 * User: beazlr02
 * Date: 09/06/2014
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public final class ApplicationCore {


    public static final String APP_START = "app_start";
    public static final String APP_SHUTDOWN = "app_shutdown";
    public static final String APP_HIDDEN = "app_hidden";
    public static final String APP_RESUMED = "app_resumed";

    public final Search search;

    // imperative shell
    public final CanDispatchSearchQuery searchService;
    public final CurrentSearchResults currentSearchResults;
    public final CurrentResult currentResult;

    public ApplicationCore(EventBus bus, MediaRepository mediaRepository) {


        search = new Search(mediaRepository, bus);

        searchService = new SearchService(bus);
        currentSearchResults = search;
        currentResult = new SelectedMediaItem(bus);
    }
}
