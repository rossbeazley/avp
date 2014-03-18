package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class ResultsFragmentScreenFactory implements FragmentScreenFactory {


    private EventBus bus;

    public ResultsFragmentScreenFactory(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        ResultsScreenAndroid result = new ResultsScreenAndroid(inflatedLayoutView);
        new ResultsScreenPresenter(result, bus);
        return result;
    }
}
