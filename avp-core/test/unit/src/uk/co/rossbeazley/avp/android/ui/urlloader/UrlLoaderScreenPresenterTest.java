package uk.co.rossbeazley.avp.android.ui.urlloader;

import org.junit.Test;
import uk.co.rossbeazley.avp.android.player.CanPlayMedia;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UrlLoaderScreenPresenterTest implements UrlLoaderScreen {

    private CanListenForUserGoEvents searchEventListener;

    private UrlLoaderScreen.CanListenForUserGotoSearchScreenEvents gotoSearchEventListener;

    private final String any_old_text = "any_old_text";
    String queryString = "NOT SET";
    private String event = "NOT_RAISED";
    private String raised = "RAISED";

    @Test
    public void invokesMediaServiceInResponseToUserSearch() {
        CanPlayMedia canPlayMedia = new CanPlayMedia() {
            @Override
            public void play(String searchString) {
                queryString = searchString;
            }
        };

        EventBus UNUSED_EVENT_BUS = null;
        new UrlLoaderScreenPresenter(this, canPlayMedia, UNUSED_EVENT_BUS);

        searchEventListener.userPressedGo();

        assertThat(queryString, is(any_old_text));
    }

    @Test
    public void dispatchesAUserWantsToSearchEventWhenClickingSearchScreenButton() {

        CanPlayMedia UNUSED_MEDIA_EVENT_SERVICE = null;

        EventBus bus = new ExecutorEventBus();

        bus.whenEvent(UrlLoaderScreenPresenter.USER_WANTS_TO_GOTO_SEARCH)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        event = raised;
                    }
                });

        // the other way to implement this is to pass a reference to a nav controller instead of sending out an event(bus)
        new UrlLoaderScreenPresenter(this, UNUSED_MEDIA_EVENT_SERVICE, bus);

        gotoSearchEventListener.userPressedGotoSearch();

        assertThat(event, is(raised));
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

    @Override
    public void setGotoSearchEventListener(CanListenForUserGotoSearchScreenEvents gotoSearchEventListener) {
        this.gotoSearchEventListener = gotoSearchEventListener;
    }
}
