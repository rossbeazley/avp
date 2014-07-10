package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.CurrentResult;
import uk.co.rossbeazley.avp.android.search.CurrentSearchResults;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsScreenPresenterTest {

    private final FakeResultsScreen fakeScreen = new FakeResultsScreen();

    private Results expectedResults = new Results();

    private EventBus bus;
    private CurrentSearchResults fakeCurrentSearch;
    private CurrentResult fakeCurrentResult = null;

    @Before
    public void setUp() throws Exception {
        fakeCurrentSearch = new CurrentSearchResults() {
            @Override
            public void announceState() {
            }
        };

        bus = new ExecutorEventBus();
    }


    @Test
    public void whenResultsAvailableListSetInScreen() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentResult);
        bus.sendPayload(expectedResults)
                .withEvent(CurrentSearchResults.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.actualResults,is(expectedResults));
    }

    @Test
    public void spinnerHiddenOnSearchComplete() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentResult);
        assertThat(fakeScreen.spinner,is(fakeScreen.SHOWN));

        bus.sendPayload(expectedResults)
                .withEvent(CurrentSearchResults.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.spinner,is(fakeScreen.HIDDEN));
    }

}
