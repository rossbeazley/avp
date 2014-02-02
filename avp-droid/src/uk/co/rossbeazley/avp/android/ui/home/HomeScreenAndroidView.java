package uk.co.rossbeazley.avp.android.ui.home;

import android.view.View;
import uk.co.rossbeazley.avp.android.R;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ViewFinder;

public class HomeScreenAndroidView implements Screen, HomeScreenView {
    private final ViewFinder viewFinder;
    private CanListenForUserSearchEvents searchEventListener;

    public HomeScreenAndroidView(CanFindViewById inflatedLayoutView) {
        searchEventListener = CanListenForUserSearchEvents.NONE;
        viewFinder = new ViewFinder(inflatedLayoutView);

        viewFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEventListener.userPressedSearch();
            }
        }, R.id.go);
    }

    @Override
    public void tearDown() {
        viewFinder.clearOnClickListener(R.id.go);
    }

    @Override
    public void setSearchEventListener(CanListenForUserSearchEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

}
