package uk.co.rossbeazley.avp.android.ui.home;

public interface HomeScreenView {
    void setSearchEventListener(CanListenForUserSearchEvents searchEventListener);

    public interface CanListenForUserSearchEvents {
        void userPressedSearch();
        CanListenForUserSearchEvents NONE = new CanListenForUserSearchEvents() { public void userPressedSearch() {} };
    }
}
