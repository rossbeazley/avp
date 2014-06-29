package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsScreenPresenterItemSelectedTest {
    private EventBus bus;

    private CurrentSearchResults currentSearchResults;
    private MediaItem expectedResult = new MediaItem("::ANY_TITLE::");
    private MediaItem selectedResult;
    private FakeResultsScreen fakeScreen;


    @Before
    public void setUp() throws Exception {
        currentSearchResults = new CurrentSearchResults() {
            @Override
            public void announceState() {
            }

            @Override
            public void selectResult(MediaItem selected) {
                selectedResult = selected;
            }
        };

        bus = new ExecutorEventBus();
        fakeScreen = new FakeResultsScreen();
    }


    @Test
    public void selectsResult() {
        new ResultsScreenPresenter(fakeScreen, bus, currentSearchResults);
        fakeScreen.listener.selected(expectedResult);
        assertThat(selectedResult, is(expectedResult));
    }

}
