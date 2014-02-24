package uk.co.rossbeazley.avp.android.ui;

public interface ScreenStack {

    void pushScreen(Class<? extends Screen> screenClass);
}
