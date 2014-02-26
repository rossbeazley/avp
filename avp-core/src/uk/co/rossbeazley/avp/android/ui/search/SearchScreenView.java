package uk.co.rossbeazley.avp.android.ui.search;

import uk.co.rossbeazley.avp.android.ui.Screen;

public interface SearchScreenView extends Screen {
    void setSearchEventListener(CanListenForUserSearchEvents searchEventListener);

    public interface CanListenForUserSearchEvents {
        void userPressedSearch();
        CanListenForUserSearchEvents NONE = new CanListenForUserSearchEvents() { public void userPressedSearch() {} };
    }
}
