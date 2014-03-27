package uk.co.rossbeazley.avp.android.application;

import java.util.Collection;

public class DependanciesInjectorRegistry {
    final DependencyInjectorMap injectors;

    public DependanciesInjectorRegistry() {
        this.injectors = new DependencyInjectorMap();
    }

    public <I> void register(Class<I> cl, DependenciesService.Injector<I> clInjector) {
        injectors.put(cl, clInjector);
    }

    public Collection<DependenciesService.Injector> injectorsForObject(Object object) {
        Class<?>[] interfaces = new InterfacesFromClass(object).invoke();
        return new InjectorsForClasses(injectors, interfaces).invoke();
    }

}
