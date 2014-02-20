package uk.co.rossbeazley.avp.android.ui.search;

import android.app.Fragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SearchNavigationControllerTest implements ScreenStack {

    private Class pushedFragmentClass;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new SearchNavigationController(this, bus);
    }

    @Test
    public void pushesHomeFragmentOnAppStart() {
        bus.announce(Events.APP_START);
        Class expected = SearchFragment.class;
        assertThat(pushedFragmentClass, is(equalTo(expected)));
    }

    public void push(Class<? extends Fragment> fragmentClass) {
        this.pushedFragmentClass = fragmentClass;
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        //To change body of imple? extends mented methods use File | Settings | File Templates.
    }

}
