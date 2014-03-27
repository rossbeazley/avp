package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class DependencyInjectorMap {

    private final Map<Class, DependenciesService.Injector> injectorsByTargetClass = new HashMap<Class, DependenciesService.Injector>();

    <Injectable> void register(Class<Injectable> injectableClass, DependenciesService.Injector<Injectable> classInjector) {
        injectorsByTargetClass.put(injectableClass, classInjector);
    }

    @SuppressWarnings("unchecked")
    <Injectable> DependenciesService.Injector<Injectable> injector(Class<Injectable> injectableClass) {
        return injectorsByTargetClass.containsKey(injectableClass) ? injectorsByTargetClass.get(injectableClass) : DependenciesService.Injector.NULL;
    }

    Collection<DependenciesService.Injector> injectorsForClasses(Class<?>[] injectableClasses) {
        ArrayList<DependenciesService.Injector> rtn = new ArrayList<DependenciesService.Injector>();

        for (Class injectableClass : injectableClasses) {
            DependenciesService.Injector classInjector = injector(injectableClass);
            rtn.add(classInjector);
        }
        return rtn;
    }
}
