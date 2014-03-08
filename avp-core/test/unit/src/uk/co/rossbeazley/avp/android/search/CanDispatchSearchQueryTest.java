package uk.co.rossbeazley.avp.android.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CanDispatchSearchQueryTest {


    private final Query ANY_QUERY = Query.fromString("any_query_string");

    private Search search;
    private Search expectedSearch = Search.fromQuery(ANY_QUERY);

    @Test
    public void testQuery() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.SEARCH_CREATED).thenRun(new FunctionWithParameter<Search>() {

            @Override
            public void invoke(Search payload) {
                search = payload;
            }
        });

        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus);

        canDispatchSearchQuery.query(ANY_QUERY);
        assertThat(search, is(expectedSearch));

    }

}
