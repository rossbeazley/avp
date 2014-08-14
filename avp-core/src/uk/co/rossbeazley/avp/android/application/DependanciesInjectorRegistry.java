package uk.co.rossbeazley.avp.android.application;

import java.util.Collection;

public final class DependanciesInjectorRegistry {
    final DependencyInjectorMap injectors;

    public DependanciesInjectorRegistry() {
        this.injectors = new DependencyInjectorMap();
    }

    public <I> DependanciesInjectorRegistry register(Class<I> cl, DependenciesService.Injector<I> clInjector) {
        injectors.put(cl, clInjector);
        return this;
    }

    public Collection<DependenciesService.Injector> injectorsForObject(Object object) {
        Class<?>[] interfaces = new InterfacesFromClass(object).invoke();
        return new InjectorsForClasses(injectors, interfaces).invoke();
    }

}
