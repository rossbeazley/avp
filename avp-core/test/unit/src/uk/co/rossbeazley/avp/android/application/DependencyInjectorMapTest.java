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

        dependencyInjectorMap.put(Class.class, injector);
        DependenciesService.Injector<Class> rtn = dependencyInjectorMap.get(Class.class);
        assertThat(rtn, is(injector));
    }

    @Test
    public void unregisteredInjector() {
        DependenciesService.Injector<DependencyInjectorMapTest> inj = dependencyInjectorMap.get(DependencyInjectorMapTest.class);
        inj.inject(this);
        assertThat(inj, is(DependenciesService.Injector.NULL));
    }
}
