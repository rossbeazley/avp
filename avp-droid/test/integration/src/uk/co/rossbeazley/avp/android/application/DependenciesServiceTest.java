package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import uk.co.rossbeazley.avp.android.ui.videoplayer.VideoPlayerFragment;
import uk.co.rossbeazley.avp.eventbus.executor.ExecutorEventBus;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependenciesServiceTest {

    @Test
    public void testInjectDependencies() throws Exception {

        Map<Class, DependenciesService.Injector> injectorsByClass = new HashMap<Class, DependenciesService.Injector>(1) {{
            put(SomeClass.class, new SomeClassInjector());
        }};

        DependenciesService ds = new DependenciesService(new ExecutorEventBus(), injectorsByClass);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isInjected(),is(true));
    }

    private class SomeClassInjector implements DependenciesService.Injector<SomeClass> {
        @Override
        public void inject(SomeClass object) {
            object.setInjected(true);
        }
    }

    private class SomeClass {
        private boolean injected = false;

        private boolean isInjected() {
            return injected;
        }

        private void setInjected(boolean injected) {
            this.injected = injected;
        }
    }
}



