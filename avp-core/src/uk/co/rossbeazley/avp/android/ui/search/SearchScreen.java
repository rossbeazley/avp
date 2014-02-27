package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.ui.Screen;

public interface SearchScreen extends Screen {
    void setSearchEventListener(CanListenForUserSearchEvents searchEventListener);

    public interface CanListenForUserSearchEvents {
        void userPressedSearch();
        CanListenForUserSearchEvents NONE = new CanListenForUserSearchEvents() { public void userPressedSearch() {} };
    }
}
