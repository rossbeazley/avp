package uk.co.rossbeazley.avp.android.application;

import java.util.HashMap;
import java.util.Map;

public class DependencyInjectors {

    private final Map<Class, DependenciesService.Injector> injectoryByTargetClass = new HashMap<Class, DependenciesService.Injector>();

    public <I> void register(Class<I> cl, DependenciesService.Injector<I> clInjector) {
        injectoryByTargetClass.put(cl, clInjector);
    }

    public <I> DependenciesService.Injector<I> injector(Class<I> cl) {
        return injectoryByTargetClass.get(cl);
    }
}
