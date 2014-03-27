package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.Query;

public class SearchScreenPresenter {

    public SearchScreenPresenter(final SearchScreen view, final CanDispatchSearchQuery canDispatchSearchQuery) {
        bindToViewSearchEvent(view, canDispatchSearchQuery);
    }

    private void bindToViewSearchEvent(final SearchScreen view, final CanDispatchSearchQuery canDispatchSearchQuery) {
        view.setSearchEventListener(new SearchScreen.CanListenForUserSearchEvents() {
            @Override
            public void userPressedSearch() {
                String searchString = view.getQueryString();
                //Could maybe show the results screen then dispatch the query
                canDispatchSearchQuery.query(Query.fromString(searchString));
            }
        });

    }

}
