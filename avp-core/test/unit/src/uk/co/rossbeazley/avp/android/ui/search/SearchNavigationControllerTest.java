package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.android.ui.urlloader.UrlLoaderScreenPresenter;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchNavigationControllerTest implements ScreenStack {

    private Class pushedClass;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new SearchNavigationController(this, bus);
    }

    @Test
    public void pushSearchFragmentOnGotoSearchEvent() {
        bus.announce(UrlLoaderScreenPresenter.USER_WANTS_TO_GOTO_SEARCH);
        Class expected = SearchScreen.class;
        assertThat(pushedClass, is(equalTo(expected)));

    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        this.pushedClass = screenClass;
    }

}
