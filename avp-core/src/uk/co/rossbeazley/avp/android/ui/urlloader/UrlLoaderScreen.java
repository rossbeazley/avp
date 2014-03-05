package uk.co.rossbeazley.avp.android.ui.urlloader;

import uk.co.rossbeazley.avp.android.ui.Screen;

public interface UrlLoaderScreen extends Screen {
    void setGoEventListener(CanListenForUserGoEvents searchEventListener);

    String uriString();

    void setGotoSearchEventListener(CanListenForUserGotoSearchScreenEvents gotoSearchEventListener);

    public interface CanListenForUserGoEvents {
        void userPressedGo();
        CanListenForUserGoEvents NONE = new CanListenForUserGoEvents() { public void userPressedGo() {} };
    }

    public interface CanListenForUserGotoSearchScreenEvents {
        void userPressedGotoSearch();
        CanListenForUserGotoSearchScreenEvents NONE = new CanListenForUserGotoSearchScreenEvents() { public void userPressedGotoSearch() {}};
    }
}
