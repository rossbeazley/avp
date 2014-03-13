package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;
import java.util.Arrays;

public class DependanciesInjectorRegistry {
    final DependencyInjectorMap injectors;

    public DependanciesInjectorRegistry(DependencyInjectorMap injectors) {
        this.injectors = injectors;
    }

    ArrayList<DependencyInjectorMap.Injector> injectorsForObject(Object object) {
        Class<?>[] interfaces = findInjectableClasses(object);
        return getInjectorsForClasses(interfaces);
    }

    private ArrayList<DependencyInjectorMap.Injector> getInjectorsForClasses(Class<?>[] injectableClasses) {
        ArrayList<DependencyInjectorMap.Injector> rtn = new ArrayList<DependencyInjectorMap.Injector>();

        for (Class injectableClass : injectableClasses) {
            DependencyInjectorMap.Injector classInjector = injectors.injector(injectableClass);
            rtn.add(classInjector);
        }
        return rtn;
    }

    private Class<?>[] findInjectableClasses(Object object) {
        ArrayList<Class<?>> rtn = new ArrayList<Class<?>>();

        Class<?> clazz = object.getClass();

        Class<?>[] interfaces = clazz.getInterfaces();
        while (clazz.getSuperclass() != null) {
            rtn.addAll(Arrays.asList(interfaces));
            clazz = clazz.getSuperclass();
            interfaces = clazz.getInterfaces();
        }

        return rtn.toArray(new Class[rtn.size()]);
    }
}