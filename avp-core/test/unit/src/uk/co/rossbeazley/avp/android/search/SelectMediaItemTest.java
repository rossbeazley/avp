package uk.co.rossbeazley.avp.android.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectMediaItemTest {

    private EventBus bus;
    private CurrentResult search;
    private MediaItem announcedResult;
    private boolean selecting;

    @Before
    public void setUp() throws Exception {
        bus = new ExecutorEventBus();
        search = new SelectedMediaItem(bus);
        selecting=false;
    }

    @Test
    public void resultSelectignAnnounced() {
        bus.whenEvent(CurrentResult.MEDIA_ITEM_SELECTING)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        selecting = true;
                    }
                });

        MediaItem expectedResult = new MediaItem("::ANY_TITLE::");
        search.selectResult(expectedResult);
        assertThat(selecting, is(true));
    }

    @Test
    public void resultSelectionAnnounced() {
        bus.whenEvent(CurrentResult.MEDIA_ITEM_AVAILABLE)
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
