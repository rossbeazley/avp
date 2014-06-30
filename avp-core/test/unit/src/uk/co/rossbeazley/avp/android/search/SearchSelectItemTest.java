package uk.co.rossbeazley.avp.android.search;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchSelectItemTest {

    private EventBus bus;
    private CurrentResult search;
    private MediaItem announcedResult;

    @Before
    public void setUp() throws Exception {
        bus = new ExecutorEventBus();

        MediaRepository unused_repo = null;
        search = new Search(unused_repo, bus);

    }

    @Test
    public void resultSelectionAnnounced() {
        bus.whenEvent(Search.MEDIA_ITEM_AVAILABLE)
                .thenRun(new FunctionWithParameter<MediaItem>() {
                    @Override
                    public void invoke(MediaItem payload) {
                        announcedResult = payload;
                    }
                });

        MediaItem expectedResult = new MediaItem("::ANY_TITLE::");
        search.selectResult(expectedResult);
        assertThat(announcedResult, is(expectedResult));
    }

}
