package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchScreenPresenterTest implements SearchScreen {

    private SearchScreen.CanListenForUserSearchEvents searchEventListener;
    private boolean invoked = false;

    @Test
    public void dispatchesEventOntoBusInResponseToUserSearch() {
        CanDispatchSearchQuery canDispatchSearchQuery = new CanDispatchSearchQuery() {
            @Override
            public void query(String searchString) {
                invoked = true;
            }
        };

        new SearchScreenPresenter(this, canDispatchSearchQuery);

        searchEventListener.userPressedSearch();

        assertThat(invoked, is(true));

    }

    @Override
    public void tearDown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSearchEventListener(CanListenForUserSearchEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

}
