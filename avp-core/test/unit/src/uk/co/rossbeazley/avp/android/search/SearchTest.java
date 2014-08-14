package uk.co.rossbeazley.avp.android.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SearchTest {

    private Results announcedResult;
    private EventBus bus;
    private Results expectedResult;
    private Query usersQuery;
    private Search search;

    @Before
    public void setUp() throws Exception {
        bus = new ExecutorEventBus();
        bus.whenEvent(CurrentSearchResults.SEARCH_RESULTS_AVAILABLE)
                .thenRun(new FunctionWithParameter<Results>() {
                    @Override
                    public void invoke(Results payload) {
                        announcedResult = payload;
                    }
                });

        expectedResult = new Results();
        usersQuery = Query.fromString("any_query");

        MediaRepository repo = new MediaRepositoryStub(new HashMap<Query, Results>(){{
            put(usersQuery, expectedResult);
        }});
        search = new Search(repo, bus);

    }

    @Test
    public void usesMediaRepositoryToRunQuery() {
        bus.sendPayload(usersQuery)
                .withEvent(SearchService.PERFORMING_QUERY);

        assertThat(announcedResult, is(expectedResult));
    }

    @Test
    public void announcesResultsStateWhenResultsAvailable() {

        bus.sendPayload(usersQuery)
                .withEvent(SearchService.PERFORMING_QUERY);

        announcedResult = null;

        search.announceState();

        assertThat(announcedResult,is(expectedResult));
    }
}
