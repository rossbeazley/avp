package uk.co.rossbeazley.avp.android.ui.search;

public interface SearchScreenView {
    void setSearchEventListener(CanListenForUserSearchEvents searchEventListener);

    public interface CanListenForUserSearchEvents {
        void userPressedSearch();
        CanListenForUserSearchEvents NONE = new CanListenForUserSearchEvents() { public void userPressedSearch() {} };
    }
}
