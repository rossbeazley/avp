package uk.co.rossbeazley.avp.android.ui.urlloader;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.player.CanPlayMedia;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UrlLoaderScreenPresenterTest implements UrlLoaderScreen {

    private CanListenForUserGoEvents searchEventListener;
    private final String any_old_text = "any_old_text";
    String queryString = "NOT SET";

    @Test
    public void dispatchesEventOntoBusInResponseToUserSearch() {
        CanPlayMedia canPlayMedia = new CanPlayMedia() {
            @Override
            public void play(String searchString) {
                queryString = searchString;
            }
        };

        new UrlLoaderScreenPresenter(this, canPlayMedia);

        searchEventListener.userPressedGo();

        assertThat(queryString, is(any_old_text));
    }

    @Override
    public String uriString() {
        return any_old_text;
    }

    @Override
    public void tearDown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTearDownEventListener(CanListenForScreenTearDownEvents canListenForScreenTearDownEvents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setGoEventListener(CanListenForUserGoEvents searchEventListener) {
        this.searchEventListener = searchEventListener;
    }

}
