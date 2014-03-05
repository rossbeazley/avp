package uk.co.rossbeazley.avp.android.ui.urlloader;

import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.player.CanPlayMedia;
import uk.co.rossbeazley.avp.eventbus.EventBus;

public class UrlLoaderScreenPresenter {

    public UrlLoaderScreenPresenter(UrlLoaderScreen view, final CanPlayMedia canDispatchSearchQuery, final EventBus bus) {
        bindToViewSearchEvent(view, canDispatchSearchQuery);

        view.setGotoSearchEventListener(new UrlLoaderScreen.CanListenForUserGotoSearchScreenEvents() {
            @Override
            public void userPressedGotoSearch() {
                bus.announce(Events.USER_PRESSED_GOTO_SEARCH);
            }
        });

    }

    private void bindToViewSearchEvent(final UrlLoaderScreen view, final CanPlayMedia canDispatchSearchQuery) {
        view.setGoEventListener(new UrlLoaderScreen.CanListenForUserGoEvents() {
            @Override
            public void userPressedGo() {
                String uriString = view.uriString();
                canDispatchSearchQuery.play(uriString);
            }
        });

    }

}
