package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependencyInjectorMapTest {

    final DependencyInjectorMap dependencyInjectorMap = new DependencyInjectorMap();

    @Test
    public void registerAndGetInjector() {
        DependencyInjectorMap.Injector<Class> injector = new DependencyInjectorMap.Injector<Class>() {
            @Override
            public void inject(Class object) { }
        };

        dependencyInjectorMap.register(Class.class, injector);
        DependencyInjectorMap.Injector<Class> rtn = dependencyInjectorMap.injector(Class.class);
        assertThat(rtn,is(injector));
    }

    @Test
    public void getRegisteredInjector() {
        DependencyInjectorMap.Injector<DependencyInjectorMapTest> inj = dependencyInjectorMap.injector(DependencyInjectorMapTest.class);
        inj.inject(this);
        assertThat(inj, is(DependencyInjectorMap.Injector.NULL));
    }
}
