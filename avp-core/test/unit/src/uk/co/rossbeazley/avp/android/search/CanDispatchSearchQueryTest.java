package uk.co.rossbeazley.avp.android.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CanDispatchSearchQueryTest {


    private String ANY_SEARCH_STRING = "any_search_string";

    private Search search;
    private Search expectedSearch = Search.fromQueryString(ANY_SEARCH_STRING);

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

        canDispatchSearchQuery.query(ANY_SEARCH_STRING);
        assertThat(search, is(expectedSearch));

    }

}
