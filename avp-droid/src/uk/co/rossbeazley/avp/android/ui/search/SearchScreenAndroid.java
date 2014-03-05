package uk.co.rossbeazley.avp.android.ui.search;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;

public class SearchScreenAndroid implements Screen, SearchScreen {
    private final ViewFinder viewFinder;
    private CanListenForUserSearchEvents searchEventListener;
    private CanListenForScreenTearDownEvents canListenForScreenTearDownEvents;

    public SearchScreenAndroid(CanFindViewById inflatedLayoutView) {
        searchEventListener = CanListenForUserSearchEvents.NONE;
        viewFinder = new ViewFinder(inflatedLayoutView);

        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEventListener.userPressedSearch();
            }
        }, R.id.go);

        this.canListenForScreenTearDownEvents = CanListenForScreenTearDownEvents.NONE;

    }

    @Override
    public void tearDown() {
        viewFinder.clearOnClickListener(R.id.go);
        canListenForScreenTearDownEvents.screenTearDown();
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        this.canListenForScreenTearDownEvents = canListenForScreenTearDownEvents;
    }

    @Override
    public void setSearchEventListener(CanListenForUserSearchEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

    @Override
    public String getQueryString() {
        return viewFinder.getText(R.id.searchString);
    }

}
