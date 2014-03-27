package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;

class InjectorsForClasses {
    private Class<?>[] interfaces;
    private DependencyInjectorMap injectors;

    InjectorsForClasses(DependencyInjectorMap injectors, Class<?>... interfaces) {
        this.injectors = injectors;
        this.interfaces = interfaces;
    }

    ArrayList<DependenciesService.Injector> invoke() {
        ArrayList<DependenciesService.Injector> rtn = new ArrayList<DependenciesService.Injector>();

        for (Class injectableClass : interfaces) {
            DependenciesService.Injector classInjector = injectors.get(injectableClass);
            rtn.add(classInjector);
        }
        return rtn;
    }
}
