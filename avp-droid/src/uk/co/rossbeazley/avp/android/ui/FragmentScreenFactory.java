package uk.co.rossbeazley.avp.android.ui;

public interface FragmentScreenFactory {
    Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView);
}
