package uk.co.rossbeazley.avp.android.application;

import java.util.Collection;

public final class DependenciesService {
    private final DependanciesInjectorRegistry dependanciesInjectorRegistry;

    public DependenciesService(DependanciesInjectorRegistry injectorRegistry) {
        dependanciesInjectorRegistry = injectorRegistry;
    }

    public void injectDependencies(Object object) {
        Collection<Injector> rtn = dependanciesInjectorRegistry.injectorsForObject(object);
        injectIntoObject(object, rtn);
    }

    private void injectIntoObject(Object object, Collection<Injector> rtn) {
        for(Injector injector : rtn) {
            injector.inject(object);
        }
    }

    public interface Injector<T> {
        void inject(T object);

        final Injector NULL = new Injector() {
            @Override
            public void inject(Object object) {}
        };
    }
}
