package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultsScreenPresenterTest implements ResultsScreen {

    private static final String SHOWN = "visible";
    private static final String HIDDEN = "hidden";
    private String spinner = "unknown";
    private Results actualResults;
    private Results expectedResults = new Results();
    private EventBus bus;

    @Before
    public void setUp() throws Exception {
        bus = new ExecutorEventBus();
        new ResultsScreenPresenter(this, bus);
    }

    @Test    //TODO should be able to drop this test and make the views default state have spinner shown
    public void directsViewToDisplaySpinnerAsDefaultState() {
        assertThat("spinner shown", spinner,is(SHOWN));
    }


    @Test
    public void whenSearchIsCompleteResultsListSetInScreen() {
        bus.sendPayload(expectedResults)
                .withEvent(Events.SEARCH_COMPLETED);

        assertThat(actualResults,is(expectedResults));
    }

    @Test
    public void spinnerHiddenOnSearchComplete() {
        assertThat(spinner,is(SHOWN));

        bus.sendPayload(expectedResults)
                .withEvent(Events.SEARCH_COMPLETED);

        assertThat(spinner,is(HIDDEN));
    }

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
