package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependencyInjectorsTest {

    final DependencyInjectors dependencyInjectors = new DependencyInjectors();

    @Test
    public void registerAndGetInjector() {
        DependencyInjectors.Injector<Class> injector = new DependencyInjectors.Injector<Class>() {
            @Override
            public void inject(Class object) { }
        };

        dependencyInjectors.register(Class.class, injector);
        DependencyInjectors.Injector<Class> rtn = dependencyInjectors.injector(Class.class);
        assertThat(rtn,is(injector));
    }
}
