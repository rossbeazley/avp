package uk.co.rossbeazley.avp.android.ui.results;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.application.DependanciesInjectorRegistry;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactoryInjector;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class ResultsFragmentDITest {

    @Test
    public void resultsFragmentDependencyRegistered() {

        DependanciesInjectorRegistry dependanciesInjectorRegistry = new DependencyInjectionFrameworkFactory().createDependanciesInjectorRegistry(new ExecutorEventBus());
        ArrayList<DependenciesService.Injector> injectors = dependanciesInjectorRegistry.injectorsForObject(new ResultsFragment());
        boolean found = false;
        for (DependenciesService.Injector injector : injectors) {
            if(injector instanceof InjectableResultsFragmentInjector) {
                found = true;
            }
        }

        assertThat(found,is(true));
    }

    @Test
    public void resultsFragmentDependencyRegistered2() {

        DependanciesInjectorRegistry dependanciesInjectorRegistry = new DependencyInjectionFrameworkFactory().createDependanciesInjectorRegistry(new ExecutorEventBus());
        ArrayList<DependenciesService.Injector> injectors = dependanciesInjectorRegistry.injectorsForObject(new ResultsFragment());
        boolean found = false;
        for (DependenciesService.Injector injector : injectors) {
            if(injector instanceof NeedsAnInflatedViewFactoryInjector) {
                found = true;
            }
        }

        assertThat(found,is(true));

        fail("Bad test name");
    }
}
