package uk.co.rossbeazley.avp.android.ui.home;

import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;

/**
* Created with IntelliJ IDEA.
* User: beazlr02
* Date: 31/01/2014
* Time: 15:37
* To change this template use File | Settings | File Templates.
*/
public class HomeFragmentScreenFactory implements FragmentScreenFactory {
    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        return new HomeScreenView(inflatedLayoutView);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
