package uk.co.rossbeazley.avp.android.media;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MediaRepositoryTest {

    MediaRepository mediaRepository;
    private ExecutorEventBus bus;
    private Map<Query, Results> resultsByQuery;
    private Results expectedResults = new Results(new MediaItem(""));
    private Query query = Query.fromString("a specific query");
    private Results actualResults;

    @Before
    public void givenAMediaRepositoryThatRespondsToOneQuery() {
        bus = new ExecutorEventBus();
        resultsByQuery = new HashMap<Query, Results>(1){{
            put(query, expectedResults);
        }};
        mediaRepository = new MediaRepositoryStub(resultsByQuery);
    }

    @Test
    public void testExecute() throws Exception {

        actualResults = mediaRepository.execute(query);

        assertThat(actualResults,is(expectedResults));
    }
}
