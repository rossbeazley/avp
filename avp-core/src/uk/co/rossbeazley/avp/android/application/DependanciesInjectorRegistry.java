package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;
import java.util.Arrays;

public class DependanciesInjectorRegistry {
    final DependencyInjectorMap injectors;

    public DependanciesInjectorRegistry() {
        this.injectors = new DependencyInjectorMap();
    }

    public <I> void register(Class<I> cl, DependenciesService.Injector<I> clInjector) {
        injectors.register(cl,clInjector);
    }

    public ArrayList<DependenciesService.Injector> injectorsForObject(Object object) {
        Class<?>[] interfaces = interfacesFromClassHieracy(object);
        return getInjectorsForClasses(interfaces);
    }

    private ArrayList<DependenciesService.Injector> getInjectorsForClasses(Class<?>[] injectableClasses) {
        ArrayList<DependenciesService.Injector> rtn = new ArrayList<DependenciesService.Injector>();

        for (Class injectableClass : injectableClasses) {
            DependenciesService.Injector classInjector = injectors.injector(injectableClass);
            rtn.add(classInjector);
        }
        return rtn;
    }

    private Class<?>[] interfacesFromClassHieracy(Object objectForInjection) {
        ArrayList<Class<?>> rtn = new ArrayList<Class<?>>();

        Class<?> currentClass = objectForInjection.getClass();

        Class<?>[] interfaces = currentClass.getInterfaces();
        while (currentClass.getSuperclass() != null) {
            rtn.addAll(Arrays.asList(interfaces));
            currentClass = currentClass.getSuperclass();
            interfaces = currentClass.getInterfaces();
        }

        return rtn.toArray(new Class[rtn.size()]);
    }
}
