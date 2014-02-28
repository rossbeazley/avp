package uk.co.rossbeazley.avp.android.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SearchServiceTest {

    private static final String ANY_STRING = "any_old_screen";
    private boolean invoked = false;

    @Test
    public void testQuery() throws Exception {
         EventBus bus = new ExecutorEventBus();

        bus.whenEvent(Events.USER_LOAD_VIDEO).thenRun(new Function() {
            @Override
            public void invoke() {
                SearchServiceTest.this.invoked = true;
            }
        });

        SearchService searchService = new DefaultSearchService(bus);

        searchService.query(ANY_STRING);
        assertThat(invoked, is(true));
    }

    private static class DefaultSearchService implements SearchService {
        private EventBus bus;

        public DefaultSearchService(EventBus bus) {
            this.bus = bus;
        }

        @Override
        public void query(String searchString) {
            UriString uriString = new UriString("http://s3-eu-west-1.amazonaws.com/mediaservices-samples/elementalGPU2_1_2/flv_avc1_med_bl__v_od_p026.mp4");
            bus .sendPayload(uriString)
                .withEvent(Events.USER_LOAD_VIDEO);
        }
    }
}
