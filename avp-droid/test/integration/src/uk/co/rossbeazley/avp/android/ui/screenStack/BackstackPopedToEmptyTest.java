package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;
import android.app.FragmentManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.ActivityForTestingViews;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class BackstackPopedToEmptyTest {

    String announced = "unannounced";

    @Test
    public void testPushFragment() throws Exception {
        ActivityForTestingViews activity = ActivityForTestingViews.createVisibleActivity();

        FragmentManager fragmentManager = activity.getFragmentManager();

        pushAnyFragment(fragmentManager);


        EventBus eventBus = new ExecutorEventBus();

        //new EmptyFragmentBackStack(fragmentManager, eventBus);
        new UiNavigationStackFactory().createNavigationViewControllers(fragmentManager, eventBus);

        eventBus.whenEvent(Events.UI_CLOSED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        announced = "announced";
                    }
                });

        fragmentManager.popBackStack();

        assertThat(announced, is("announced"));
    }

    private void pushAnyFragment(FragmentManager fragmentManager) {
        new FragmentManagerTransaction(fragmentManager).addFragmentToBackStack(new Fragment());
    }

}
