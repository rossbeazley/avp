package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Before;
import org.junit.Test;
import uk.co.rossbeazley.avp.Events;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;
import uk.co.rossbeazley.avp.eventbus.EventBus;
import uk.co.rossbeazley.avp.eventbus.Function;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResultsNavigationControllerTest implements ScreenStack {

    private Class pushedClass;
    private EventBus bus;

    @Before
    public void setup() {
        bus = new ExecutorEventBus();
        new ResultsNavigationController(this, bus);
    }

    @Test
    public void pushResultsScreenWhenSearchCreated() {
        bus.announce(Events.SEARCH_CREATED);
        Class expected = ResultsScreen.class;
        assertThat(pushedClass, is(equalTo(expected)));
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        this.pushedClass = screenClass;
    }

    private class ResultsNavigationController {

        public ResultsNavigationController(final ScreenStack screenStack, final EventBus bus) {
            bus.whenEvent(Events.SEARCH_CREATED)
                    .thenRun(new Function() {
                        @Override
                        public void invoke() {
                            screenStack.pushScreen(ResultsScreen.class); //KEVIN - my ui navigation has just broken,
                                                                         // how do i get the payload into the presenter and thus the screen?
                                                                         // for now I can show nothing and wait for the results
                                                                         // maybe my presenters are singletons?
                        }
                    });
        }
    }

}
