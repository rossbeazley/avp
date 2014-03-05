package uk.co.rossbeazley.avp.android.search;

import org.junit.Ignore;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class CanDispatchSearchQueryTest {


    private Search search;
    private Search expectedSearch = null;
    private String ANY_SEARCH_STRING = "any_search_string";

    @Test
    public void testQuery() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.USER_SEARCH_CREATED).thenRun(new FunctionWithParameter<Search>() {

            @Override
            public void invoke(Search payload) {
                search = payload;
            }
        });

        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus);

        canDispatchSearchQuery.query(ANY_SEARCH_STRING);
        assertThat(search, is(expectedSearch));

    }

    private class Search {
    }
}
