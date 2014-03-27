package uk.co.rossbeazley.avp.android.application;

import java.util.HashMap;
import java.util.Map;

class DependencyInjectorMap {

    private final Map<Class, DependenciesService.Injector> injectorsByTargetClass = new HashMap<Class, DependenciesService.Injector>();

    public <Injectable> void register(Class<Injectable> cl, DependenciesService.Injector<Injectable> clInjector) {
        injectorsByTargetClass.put(cl, clInjector);
    }

    @SuppressWarnings("unchecked")
    public <I> DependenciesService.Injector<I> injector(Class<I> cl) {
        return injectorsByTargetClass.containsKey(cl) ? injectorsByTargetClass.get(cl) : DependenciesService.Injector.NULL;
    }

}
