package uk.co.rossbeazley.avp.android.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

    private Results announcedResult;

    @Test
    public void usesMediaRepositoryToRunQuery() {
        EventBus bus = new ExecutorEventBus();
        bus.whenEvent(Events.SEARCH_COMPLETED)
                .thenRun(new FunctionWithParameter<Results>() {
                    @Override
                    public void invoke(Results payload) {
                        announcedResult = payload;
                    }
                });

        final Results expectedResult = new Results();

        final Query usersQuery = Query.fromString("any_query");

        MediaRepository repo = new MediaRepositoryStub(new HashMap<Query, Results>(){{
            put(usersQuery,expectedResult);
        }});

        new Search(repo, bus);

        bus.sendPayload(usersQuery)
                .withEvent(Events.USER_QUERY);

        assertThat(announcedResult, is(expectedResult));
    }
}
