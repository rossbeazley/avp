package uk.co.rossbeazley.avp.android.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SearchNoResultsTest {

    private Results announcedResult;
    private EventBus bus;
    private Search search;
    private Results noResult =  new Results();

    private static final MediaRepository UNUSED_REPOSITORY = null;

    @Before
    public void setUp() throws Exception {
        bus = new ExecutorEventBus();
        search = new Search(UNUSED_REPOSITORY, bus);

    }

    @Test
    public void noResultsOnConstruction() {

        announcedResult = null;

        bus.whenEvent(CurrentSearchResults.NO_SEARCH_RESULTS_AVAILABLE)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        announcedResult = noResult;
                    }
                });

        search.announceState();

        assertThat(announcedResult,is(noResult));
    }
}
