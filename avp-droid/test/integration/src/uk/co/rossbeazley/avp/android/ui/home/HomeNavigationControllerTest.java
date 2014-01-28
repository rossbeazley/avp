package uk.co.rossbeazley.avp.android.ui.home;

import android.app.Fragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.FragmentStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeNavigationControllerTest implements FragmentStack {

    private Class pushedFragmentClass;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new HomeNavigationController(this, bus);
    }

    @Test
    public void pushesHomeFragmentOnAppStart() {
        bus.announce(Events.APP_START);
        Class expected = HomeFragment.class;
        assertThat(pushedFragmentClass, is(equalTo(expected)));
    }

    @Override
    public void pushFragment(Class<? extends Fragment> fragmentClass) {
        this.pushedFragmentClass = fragmentClass;
    }

}
