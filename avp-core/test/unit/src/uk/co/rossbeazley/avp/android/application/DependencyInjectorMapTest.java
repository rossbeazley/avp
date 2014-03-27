package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependencyInjectorMapTest {

    final DependencyInjectorMap dependencyInjectorMap = new DependencyInjectorMap();

    @Test
    public void registerAndGetInjector() {
        DependenciesService.Injector<Class> injector = new DependenciesService.Injector<Class>() {
            @Override
            public void inject(Class object) { }
        };

        dependencyInjectorMap.register(Class.class, injector);
        DependenciesService.Injector<Class> rtn = dependencyInjectorMap.injector(Class.class);
        assertThat(rtn,is(injector));
    }

    @Test
    public void unregisteredInjector() {
        DependenciesService.Injector<DependencyInjectorMapTest> inj = dependencyInjectorMap.injector(DependencyInjectorMapTest.class);
        inj.inject(this);
        assertThat(inj, is(DependenciesService.Injector.NULL));
    }
}
