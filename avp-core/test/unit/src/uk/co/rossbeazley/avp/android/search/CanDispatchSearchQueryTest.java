package uk.co.rossbeazley.avp.android.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CanDispatchSearchQueryTest {

    private static final String ANY_STRING = "any_old_screen";
    private boolean invoked = false;

    @Test
    public void testQuery() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.USER_LOAD_VIDEO).thenRun(new Function() {
            @Override
            public void invoke() {
                CanDispatchSearchQueryTest.this.invoked = true;
            }
        });

        CanDispatchSearchQuery canDispatchSearchQuery = new SearchService(bus);

        canDispatchSearchQuery.query(ANY_STRING);
        assertThat(invoked, is(true));
    }

}
