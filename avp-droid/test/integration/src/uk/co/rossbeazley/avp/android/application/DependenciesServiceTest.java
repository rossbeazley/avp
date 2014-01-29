package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependenciesServiceTest {

    @Test
    public void testInjectDependencies() throws Exception {

        final SomeClassInjector injector = new SomeClassInjector();

        DependencyInjectors injectors = new DependencyInjectors(){{
            register(SomeClass.class, injector);
        }};

        DependenciesService ds = new DependenciesService(injectors);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isInjected(), is(true));
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



