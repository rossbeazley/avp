package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependenciesServiceTest {

    @Test
    public void testInjectDependenciesDefinedByInterface() throws Exception {

        final SomeClassInjector injector = new SomeClassInjector();

        DependencyInjectors injectors = new DependencyInjectors(){{
            register(InjectableSomeClass.class, injector);
        }};

        DependenciesService ds = new DependenciesService(injectors);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isInjected(), is(true));
    }

    @Test
    public void testInjectDependenciesDefinedByInterfaceNotFound() throws Exception {

        DependencyInjectors injectors = new DependencyInjectors();

        DependenciesService ds = new DependenciesService(injectors);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(true, is(true));
    }

    private class SomeClassInjector implements DependencyInjectors.Injector<InjectableSomeClass> {
        @Override
        public void inject(InjectableSomeClass object) {
            object.setInjected(true);
        }
    }

    private class SomeClass implements InjectableSomeClass {
        private boolean injected = false;

        private boolean isInjected() {
            return injected;
        }

        public void setInjected(boolean injected) {
            this.injected = injected;
        }
    }

    interface InjectableSomeClass {
        public void setInjected(boolean injected);
    }
}



