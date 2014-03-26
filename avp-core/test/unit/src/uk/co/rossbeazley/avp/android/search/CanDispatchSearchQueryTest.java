package uk.co.rossbeazley.avp.android.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CanDispatchSearchQueryTest {


    private final Query ANY_QUERY = Query.fromString("any_query_string");

    private Search searchAnnounced;
    private Search searchExecuted;
    private Search expectedSearch = Search.fromQuery(ANY_QUERY);
    private MediaRepository mediaRepository;
    private EventBus bus;
    private CanDispatchSearchQuery canDispatchSearchQuery;


    @Before
    public void setUp() throws Exception {
        searchAnnounced = null;
        searchExecuted = null;
        bus = new ExecutorEventBus();

        mediaRepository = new MediaRepository() {
            @Override public void execute(Search search) {
                CanDispatchSearchQueryTest.this.searchExecuted = search;
            }
        };

        bus.whenEvent(Events.SEARCH_CREATED)
                .thenRun(new FunctionWithParameter<Search>() {
            @Override public void invoke(Search payload) {
                searchAnnounced = payload;
            }
        });

        canDispatchSearchQuery = new SearchService(bus, mediaRepository);
    }

    @Test
    public void tellsTheWorldASearchHasBeenCreated() throws Exception {
        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(searchAnnounced, is(expectedSearch));
    }

    @Test
    public void executesTheSearchUsingTheMediaRepository() {
        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(searchExecuted,is(expectedSearch));
    }

}
