package uk.co.rossbeazley.avp.android.application;

import java.util.HashMap;
import java.util.Map;

class DependencyInjectorMap {

    private final Map<Class, DependenciesService.Injector> injectorsByTargetClass = new HashMap<Class, DependenciesService.Injector>();

    <Injectable> void put(Class<Injectable> injectableClass, DependenciesService.Injector<Injectable> classInjector) {
        injectorsByTargetClass.put(injectableClass, classInjector);
    }

    @SuppressWarnings("unchecked")
    <Injectable> DependenciesService.Injector<Injectable> get(Class<Injectable> injectableClass) {
        return injectorsByTargetClass.containsKey(injectableClass) ? injectorsByTargetClass.get(injectableClass) : DependenciesService.Injector.NULL;
    }

}
