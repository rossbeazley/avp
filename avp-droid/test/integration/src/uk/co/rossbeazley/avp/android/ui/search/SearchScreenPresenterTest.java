package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchScreenPresenterTest implements SearchScreen {

    private CanListenForUserSearchEvents searchEventListener;
    private boolean invoked = false;

    @Test
    public void dispatchesEventOntoBusInResponseToUserSearch() {
        EventBus bus = new ExecutorEventBus();
        SearchScreenPresenter presenter = new SearchScreenPresenter(this, bus);

        bus.whenEvent(Events.USER_LOAD_VIDEO).thenRun(new Function() {
            @Override
            public void invoke() {
                SearchScreenPresenterTest.this.invoked = true;
            }
        });

        searchEventListener.userPressedSearch();

        assertThat(invoked, is(true));

    }

    @Override
    public void setSearchEventListener(CanListenForUserSearchEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

    @Override
    public void tearDown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
