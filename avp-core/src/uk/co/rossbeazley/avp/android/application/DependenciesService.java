package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;

public class DependenciesService {
    private final DependanciesInjectorRegistry dependanciesInjectorRegistry;

    public DependenciesService(DependanciesInjectorRegistry injectorRegistry) {
        //dependanciesInjectorRegistry = new DependanciesInjectorRegistry(injectorRegistry);
        dependanciesInjectorRegistry = injectorRegistry;
    }

    public void injectDependencies(Object object) {
        ArrayList<DependencyInjectorMap.Injector> rtn = dependanciesInjectorRegistry.injectorsForObject(object);
        injectIntoObject(object, rtn);
        //Use double dispatch to avoid this dodgy code? or is DD more of problem, gets everywhere then
    }

    private void injectIntoObject(Object object, ArrayList<DependencyInjectorMap.Injector> rtn) {
        for(DependencyInjectorMap.Injector injector : rtn) {
            injector.inject(object);
        }
    }

}
