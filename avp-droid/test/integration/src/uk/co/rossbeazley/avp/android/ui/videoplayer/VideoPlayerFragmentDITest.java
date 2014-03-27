package uk.co.rossbeazley.avp.android.ui.videoplayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.application.DependanciesInjectorRegistry;
import uk.co.rossbeazley.avp.android.application.DependenciesService;
import uk.co.rossbeazley.avp.android.application.DependencyInjectionFrameworkFactory;
import uk.co.rossbeazley.avp.android.ui.NeedsAnInflatedViewFactoryInjector;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class VideoPlayerFragmentDITest {

    private Collection<DependenciesService.Injector> injectors;

    @Before
    public void setUp() throws Exception {
        DependanciesInjectorRegistry dependanciesInjectorRegistry= new DependencyInjectionFrameworkFactory().createDependanciesInjectorRegistry(new ExecutorEventBus());
        injectors = dependanciesInjectorRegistry.injectorsForObject(getObject());
    }

    @Test
    public void videoPlayerFragmentInjectorRegistered() {
        boolean found = false;
        for (DependenciesService.Injector injector : injectors) {
            if(injector instanceof VideoPlayerFragmentInjector) {
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
        return new VideoPlayerFragment();
    }
}
