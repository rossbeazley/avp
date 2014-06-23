package uk.co.rossbeazley.avp.android.ui.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.ApplicationCore;
import uk.co.rossbeazley.avp.android.application.DependanciesInjectorRegistry;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactoryInjector;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SearchFragmentDITest {

    private Collection<DependenciesService.Injector> injectors;

    @Before
    public void setUp() throws Exception {
        ApplicationCore UNUSED_APP_CORE = null;
        DependanciesInjectorRegistry dependanciesInjectorRegistry= new DependencyInjectionFrameworkFactory().createDependanciesInjectorRegistry(new ExecutorEventBus(), UNUSED_APP_CORE);
        injectors = dependanciesInjectorRegistry.injectorsForObject(getObject());
    }

    @Test
    public void searchFragmentInjectorRegistered() {
        boolean found = false;
        for (DependenciesService.Injector injector : injectors) {
            if(injector instanceof SearchFragmentInjector) {
                found = true;
            }
        }

        assertThat(found, is(true));
    }

    @Test
    public void inflatedViewFactoryRegistered() {

        boolean found = false;
        for (DependenciesService.Injector injector : injectors) {
            if(injector instanceof NeedsAnInflatedViewFactoryInjector) {
                found = true;
            }
        }

        assertThat(found,is(true));

    }

    private Object getObject() {
        return new SearchFragment();
    }
}
