package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.UriString;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class SearchScreenPresenter {

    public SearchScreenPresenter(SearchScreen view, final CanDispatchSearchQuery canDispatchSearchQuery) {
        bindToViewSearchEvent(view, canDispatchSearchQuery);
    }

    private void bindToViewSearchEvent(final SearchScreen view, final CanDispatchSearchQuery canDispatchSearchQuery) {
        view.setSearchEventListener(new SearchScreen.CanListenForUserSearchEvents() {
            @Override
            public void userPressedSearch() {
                String searchString = view.getQueryString();
                canDispatchSearchQuery.query(searchString);
            }
        });

    }

}
