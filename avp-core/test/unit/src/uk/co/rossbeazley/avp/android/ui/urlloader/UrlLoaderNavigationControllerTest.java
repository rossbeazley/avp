package uk.co.rossbeazley.avp.android.ui.urlloader;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UrlLoaderNavigationControllerTest implements ScreenStack {

    private Class pushedClass;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new UrlLoaderNavigationController(this, bus);
    }

    @Test
    public void pushesHomeFragmentOnAppStart() {
        bus.announce(ApplicationCore.APP_START);
        Class expected = UrlLoaderScreen.class;
        assertThat(pushedClass, is(equalTo(expected)));
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        this.pushedClass = screenClass;
    }

}
