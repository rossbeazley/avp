package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.Query;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SearchScreenPresenterTest implements SearchScreen {

    private SearchScreen.CanListenForUserSearchEvents searchEventListener;
    private final String any_old_text = "any_old_text";
    String queryString = "NOT SET";

    @Test
    public void dispatchesEventOntoBusInResponseToUserSearch() {
        CanDispatchSearchQuery canDispatchSearchQuery = new CanDispatchSearchQuery() {
            @Override
            public void query(Query query) {
                queryString = query.string();
            }
        };

        new SearchScreenPresenter(this, canDispatchSearchQuery);

        searchEventListener.userPressedSearch();

        assertThat(queryString, is(any_old_text));
    }

    @Override
    public String getQueryString() {
        return any_old_text;
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
