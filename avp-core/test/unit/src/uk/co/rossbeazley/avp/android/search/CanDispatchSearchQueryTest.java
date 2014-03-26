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

    private Search search;
    private Search expectedSearch = Search.fromQuery(ANY_QUERY);


    @Before
    public void setUp() throws Exception {
        search = null;
    }

    @Test
    public void tellsTheWorldASearchHasBeenCreated() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.SEARCH_CREATED).thenRun(new FunctionWithParameter<Search>() {

            @Override
            public void invoke(Search payload) {
                search = payload;
            }
        });

        MediaRepository mediaRepository = new MediaRepository() {
            @Override
            public void execute(Search search) {
            }
        };
        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus, mediaRepository);

        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(search, is(expectedSearch));

    }

    @Test
    public void executesTheSearchUsingTheMediaRepository() {

        MediaRepository mediaRepository = new MediaRepository() {
            @Override public void execute(Search search) {
                CanDispatchSearchQueryTest.this.search = search;
            }
        };

        EventBus bus = new ExecutorEventBus();
        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus,mediaRepository);


        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(search,is(expectedSearch));
    }

}
