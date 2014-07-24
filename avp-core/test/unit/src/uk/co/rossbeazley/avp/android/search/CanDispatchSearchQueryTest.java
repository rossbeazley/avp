package uk.co.rossbeazley.avp.android.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CanDispatchSearchQueryTest {


    private final Query ANY_QUERY = Query.fromString("any_query_string");

    private Query queryAnnounced;
    private EventBus bus;
    private CanDispatchSearchQuery canDispatchSearchQuery;


    @Before
    public void setUp() throws Exception {
        queryAnnounced = null;
        bus = new ExecutorEventBus();

        bus.whenEvent(SearchService.PERFORMING_QUERY)
                .thenRun(new FunctionWithParameter<Query>() {
            @Override public void invoke(Query payload) {
                queryAnnounced = payload;
            }
        });

        canDispatchSearchQuery = new SearchService(bus);
    }

    @Test
    public void tellsTheWorldASearchHasBeenCreated() throws Exception {
        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(queryAnnounced, is(ANY_QUERY));
    }

}
