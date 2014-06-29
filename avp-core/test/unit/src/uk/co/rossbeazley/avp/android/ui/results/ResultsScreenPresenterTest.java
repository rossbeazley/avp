package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.media.MediaItem;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.search.Search;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsScreenPresenterTest {

    private final FakeResultsScreen fakeScreen = new FakeResultsScreen();

    private Results expectedResults = new Results();

    private EventBus bus;
    private CurrentSearchResults fakeCurrentSearch;

    @Before
    public void setUp() throws Exception {
        fakeCurrentSearch = new CurrentSearchResults() {
            @Override
            public void announceState() {
            }

            @Override
            public void selectResult(MediaItem selected) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        bus = new ExecutorEventBus();
    }


    @Test
    public void whenSearchIsCompleteResultsListSetInScreen() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentSearch);
        bus.sendPayload(expectedResults)
                .withEvent(Search.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.actualResults,is(expectedResults));
    }

    @Test
    public void spinnerHiddenOnSearchComplete() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentSearch);
        assertThat(fakeScreen.spinner,is(fakeScreen.SHOWN));

        bus.sendPayload(expectedResults)
                .withEvent(Search.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.spinner,is(fakeScreen.HIDDEN));
    }

    @Test
    public void whenResultsAvailableListSetInScreen() {
        new ResultsScreenPresenter(fakeScreen, bus, new CurrentSearchResults() {
            @Override
            public void announceState() {
                bus.sendPayload(expectedResults).withEvent(Search.SEARCH_RESULTS_AVAILABLE);
            }

            @Override
            public void selectResult(MediaItem selected) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        assertThat(fakeScreen.actualResults,is(expectedResults));
    }

}
