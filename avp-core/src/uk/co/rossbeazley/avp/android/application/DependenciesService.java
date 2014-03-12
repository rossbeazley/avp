package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;
import java.util.Arrays;

public class DependenciesService {
    private final DependencyInjectors injectors;

    public DependenciesService(DependencyInjectors injectors) {
        this.injectors = injectors;
    }

    public void injectDependencies(Object object) {
        Class<?>[] interfaces = findInjectableClasses(object);
        injectEachClass(object, interfaces);
    }

    @SuppressWarnings("unchecked")
    private void injectEachClass(Object object, Class<?>[] injectableClasses) {
        for (Class injectableClass : injectableClasses) {
            DependencyInjectors.Injector classInjector = injectors.injector(injectableClass);
            classInjector.inject(object);    //Use double dispatch to avoid this dodgy code? or is DD more of problem, gets everywhere then
        }
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
