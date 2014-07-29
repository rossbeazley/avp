package uk.co.rossbeazley.avp.android.application;


import android.os.Bundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.ui.screenStack.EmptyFragmentBackStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ApplicationSaveRestoreStateTest {

    private static final String APP_RESUMED = "app_resumed";
    private static final String APP_START = "app_start";
    private String appState;
    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        eventBus = new ExecutorEventBus();

        eventBus.whenEvent(ApplicationCore.APP_START)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        appState = APP_START;
                    }
                });


        eventBus.whenEvent(ApplicationCore.APP_RESUMED)
                .thenRun(new Function() {
                    @Override
                    public void invoke() {
                        appState = APP_RESUMED;
                    }
                });
    }

    @Test
    public void savesRunningIndicatorIntoBundle() {
        Bundle inState = new Bundle();
        Bundle outState = new Bundle();
        new ApplicationUIState(inState,eventBus).stateIntoBundle(outState);
        assertTrue(outState.containsKey(ApplicationUIState.RUNNING));
    }

    @Test
    public void bundleWithRestoreKeyIsStateRestore() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ApplicationUIState.RUNNING, true);

        new ApplicationUIState(bundle, eventBus);

        assertThat(appState, is(APP_RESUMED));
    }

    @Test
    public void bundleWithNoRestoreKeyIsStateStart() {

        Bundle bundle = new Bundle();

        new ApplicationUIState(bundle, eventBus);

        assertThat(appState, is(APP_START));
    }
    @Test
    public void bundleWithNullBundleIsStateStart() {

        Bundle bundle = null;

        new ApplicationUIState(bundle, eventBus);

        assertThat(appState, is(APP_START));
    }

    @Test
    public void stateNotRunningWhenUICloses() {

        ApplicationUIState applicationUIState = new ApplicationUIState(new Bundle(), eventBus);
        eventBus.announce(EmptyFragmentBackStack.UI_CLOSED);

        Bundle outState = new Bundle();
        applicationUIState.stateIntoBundle(outState);

        assertFalse(outState.containsKey(ApplicationUIState.RUNNING));
    }

}
