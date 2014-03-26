package uk.co.rossbeazley.avp.android.media;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaRepositoryTest {

    MediaRepository mediaRepository;
    private ExecutorEventBus bus;
    private Map<Query, Results> resultsByQuery;
    private Results expectedResults = new Results(new Programme());
    private Query query = Query.fromString("a specific query");
    private Results actualResults;

    @Before
    public void givenAMediaRepositoryThatRespondsToOneQuery() {
        bus = new ExecutorEventBus();
        resultsByQuery = new HashMap<Query, Results>(1){{
            put(query, expectedResults);
        }};
        mediaRepository = new MediaRepositoryStub(bus,resultsByQuery);
    }

    @Test
    public void testExecute() throws Exception {

        bus.whenEvent(Events.SEARCH_COMPLETED)
                .thenRun(new FunctionWithParameter<Search>() {
                    @Override public void invoke(Search payload) {
                        actualResults = payload.results();
                    }
                });

        mediaRepository.execute(Search.fromQuery(query));

        assertThat(actualResults,is(expectedResults));
    }
}
