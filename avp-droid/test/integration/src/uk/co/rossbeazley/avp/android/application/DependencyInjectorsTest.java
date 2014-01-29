package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependencyInjectorsTest {

    final DependencyInjectors dependencyInjectors = new DependencyInjectors();

    @Test
    public void registerAndGetInjector() {
        DependenciesService.Injector<Class> injector = new DependenciesService.Injector<Class>() {
            @Override
            public void inject(Class object) { }
        };

        dependencyInjectors.register(Class.class, injector);
        DependenciesService.Injector<Class> rtn = dependencyInjectors.injector(Class.class);
        assertThat(rtn,is(injector));
    }
}
