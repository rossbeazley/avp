package uk.co.rossbeazley.avp.android.application;

import java.util.HashMap;
import java.util.Map;

class DependencyInjectorMap {

    private final Map<Class, DependenciesService.Injector> injectorsByTargetClass = new HashMap<Class, DependenciesService.Injector>();

    public <Injectable> void register(Class<Injectable> injectableClass, DependenciesService.Injector<Injectable> classInjector) {
        injectorsByTargetClass.put(injectableClass, classInjector);
    }

    @SuppressWarnings("unchecked")
    public <Injectable> DependenciesService.Injector<Injectable> injector(Class<Injectable> injectableClass) {
        return injectorsByTargetClass.containsKey(injectableClass) ? injectorsByTargetClass.get(injectableClass) : DependenciesService.Injector.NULL;
    }

}
