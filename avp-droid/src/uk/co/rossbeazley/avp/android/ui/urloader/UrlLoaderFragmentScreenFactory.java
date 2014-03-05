package uk.co.rossbeazley.avp.android.ui.urloader;

import uk.co.rossbeazley.avp.android.player.CanPlayMedia;
import uk.co.rossbeazley.avp.android.player.MediaService;
import uk.co.rossbeazley.avp.android.search.CanDispatchSearchQuery;
import uk.co.rossbeazley.avp.android.search.SearchService;
import uk.co.rossbeazley.avp.android.ui.CanFindViewById;
import uk.co.rossbeazley.avp.android.ui.FragmentScreenFactory;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.search.SearchScreenPresenter;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class UrlLoaderFragmentScreenFactory implements FragmentScreenFactory {
    private EventBus bus;

    public UrlLoaderFragmentScreenFactory(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public Screen buildScreenWithInflatedView(CanFindViewById inflatedLayoutView) {
        UrlLoaderScreenAndroid result = new UrlLoaderScreenAndroid(inflatedLayoutView);
        CanPlayMedia canPlayMedia = new MediaService(bus); //TODO just newing the edge of the app here, dosnt feel quite right
        new UrlLoaderScreenPresenter(result, canPlayMedia);
        return result;
    }
}
