package uk.co.rossbeazley.avp.android.ui.urlloader;

import uk.co.rossbeazley.avp.android.player.CanPlayMedia;

public class UrlLoaderScreenPresenter {

    public UrlLoaderScreenPresenter(UrlLoaderScreen view, final CanPlayMedia canDispatchSearchQuery) {
        bindToViewSearchEvent(view, canDispatchSearchQuery);
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
