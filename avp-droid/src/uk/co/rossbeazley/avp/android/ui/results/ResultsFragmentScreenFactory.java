package uk.co.rossbeazley.avp.android.ui.results;

import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class ResultsFragmentScreenFactory implements FragmentScreenFactory {


    private EventBus bus;
    private ApplicationCore applicationCore;

    public ResultsFragmentScreenFactory(EventBus bus, ApplicationCore applicationCore) {
        this.bus = bus;
        this.applicationCore = applicationCore;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        ResultsScreenAndroid view = new ResultsScreenAndroid(inflatedLayoutView);
        new ResultsScreenPresenter(view, bus, applicationCore.currentSearchResults, applicationCore.currentResult);
        return view;
    }
}
