package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
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

    @Before
    public void setUp() throws Exception {
        fakeCurrentSearch = new CurrentSearchResults() {
            @Override
            public void announceState() {
            }
        };

        bus = new ExecutorEventBus();
    }

    @Test    //TODO should be able to drop this test and make the views default state have spinner shown
    public void directsViewToDisplaySpinnerAsDefaultStateIfResultsLoading() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentSearch);
        assertThat("spinner shown", fakeScreen.spinner,is(fakeScreen.SHOWN));
    }


    @Test
    public void whenSearchIsCompleteResultsListSetInScreen() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentSearch);
        bus.sendPayload(expectedResults)
                .withEvent(Events.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.actualResults,is(expectedResults));
    }

    @Test
    public void spinnerHiddenOnSearchComplete() {
        new ResultsScreenPresenter(fakeScreen, bus, fakeCurrentSearch);
        assertThat(fakeScreen.spinner,is(fakeScreen.SHOWN));

        bus.sendPayload(expectedResults)
                .withEvent(Events.SEARCH_RESULTS_AVAILABLE);

        assertThat(fakeScreen.spinner,is(fakeScreen.HIDDEN));
    }

    @Test
    public void whenResultsAvailableListSetInScreen() {
        new ResultsScreenPresenter(fakeScreen, bus, new CurrentSearchResults() {
            @Override
            public void announceState() {
                bus.sendPayload(expectedResults).withEvent(Events.SEARCH_RESULTS_AVAILABLE);
            }
        });
        assertThat(fakeScreen.actualResults,is(expectedResults));
    }

    private class FakeResultsScreen implements ResultsScreen {
        public Results actualResults;
        public static final String SHOWN = "visible";
        public static final String HIDDEN = "hidden";
        public String spinner = "unknown";

        @Override
        public void showResults(Results results) {
            actualResults = results;
        }

        @Override
        public void showSpinner() {
            spinner = SHOWN;
        }

        @Override
        public void hideSpinner() {
            spinner = HIDDEN;
        }

        @Override
        public void tearDown() {
        }

        @Override
        public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        }
    }
}
