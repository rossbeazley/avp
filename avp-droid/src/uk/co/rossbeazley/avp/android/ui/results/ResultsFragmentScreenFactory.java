package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;

public class ResultsFragmentScreenFactory implements FragmentScreenFactory {
    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        ResultsScreenAndroid result = new ResultsScreenAndroid(inflatedLayoutView);
        new ResultsScreenPresenter(result, bus);
        return result;
    }
}
