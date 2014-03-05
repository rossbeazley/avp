package uk.co.rossbeazley.avp.android.ui.urlloader;

import uk.co.rossbeazley.avp.android.ui.Screen;

public interface UrlLoaderScreen extends Screen {
    void setGoEventListener(CanListenForUserGoEvents searchEventListener);

    String uriString();

    public interface CanListenForUserGoEvents {
        void userPressedGo();
        CanListenForUserGoEvents NONE = new CanListenForUserGoEvents() { public void userPressedGo() {} };
    }
}
