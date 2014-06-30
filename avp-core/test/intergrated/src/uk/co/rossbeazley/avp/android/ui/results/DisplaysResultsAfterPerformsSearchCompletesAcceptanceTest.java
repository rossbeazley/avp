package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Test;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.media.MediaRepository;
import uk.co.rossbeazley.avp.android.media.MediaRepositoryStub;
import uk.co.rossbeazley.avp.android.player.creator.MediaPlayerFactory;
import uk.co.rossbeazley.avp.android.player.time.CanExecuteCommandsAtFixedRate;
import uk.co.rossbeazley.avp.android.search.Query;
import uk.co.rossbeazley.avp.android.search.Results;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class DisplaysResultsAfterPerformsSearchCompletesAcceptanceTest implements ResultsScreen{

    private Results results;
    private ScreenFragmentTransaction screenFragmentTransaction;
    private ApplicationCore applicationCore;

    @Test
    public void dispatchesQueryAndDisplaysResults() {

        final EventBus bus = new ExecutorEventBus();

        ScreenStack screenStack = new ScreenStack() {
            @Override
            public void pushScreen(Class<? extends Screen> screenClass) {
                screenFragmentTransaction = new ScreenFragmentTransaction(DisplaysResultsAfterPerformsSearchCompletesAcceptanceTest.this, bus);
            }
        };

        new ResultsNavigationController(screenStack, bus);

        MediaRepository repo = MediaRepositoryStub.createMediaRepository();

        final MediaPlayerFactory UNUSED_MP_FACTORY = null;
        final CanExecuteCommandsAtFixedRate UNUSED_EXECUTOR = null;

        applicationCore = new ApplicationCore(bus, UNUSED_MP_FACTORY, UNUSED_EXECUTOR, repo);

        Query query = Query.fromString("ross");
        applicationCore.searchService.query(query);

        screenFragmentTransaction.commit();

        assertThat(results, is(not(nullValue())));
    }

    @Override
    public void showSpinner() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void showResults(Results results) {
        this.results = results;
    }

    @Override
    public void hideSpinner() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setResultSelectedListener(CanListenForResultSelection listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void tearDown() { }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) { }

    private class ScreenFragmentTransaction {
        private final ResultsScreen resultsScreen;
        private final EventBus eventBus;

        public ScreenFragmentTransaction(ResultsScreen resultsScreen, EventBus eventBus) {
            this.resultsScreen = resultsScreen;
            this.eventBus = eventBus;
        }

        public void commit() {
            new ResultsScreenPresenter(resultsScreen, eventBus, applicationCore.currentSearchResults, applicationCore.currentResult);
        }
    }
}
