package uk.co.rossbeazley.avp.android.ui.playout;

import org.hamcrest.Matcher;
import org.junit.Test;
import uk.co.rossbeazley.avp.android.application.ApplicationServices;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.application.NullApplicationServices;
import uk.co.rossbeazley.avp.eventbus.DefaultEventBus;
import uk.co.rossbeazley.avp.eventbus.EventBus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EventBusInjectorTest implements InjectableEventBus {
    private EventBus actualEventBus;

    @Override
    public void injectEventBus(EventBus eventBus) {

        actualEventBus = eventBus;
    }

    @Test
    public void systemInjectsEventBus()
    {
        final EventBus expectedEventBus = new DefaultEventBus();
        ApplicationServices services = new NullApplicationServices() {
            @Override
            public EventBus eventbus() {
                return expectedEventBus;
            }
        };

        DependenciesService ds = new DependencyInjectionFrameworkFactory().createDependencyInjectionFramework(services, null);

        ds.injectDependencies(this);

        assertThat(actualEventBus, is(expectedEventBus));
    }

}
