package uk.co.rossbeazley.avp.android.application;

public class DependenciesService {
    private final DependencyInjectors injectors;

    public DependenciesService(DependencyInjectors injectors) {
        this.injectors = injectors;
    }

    @SuppressWarnings("unchecked")
    void injectDependencies(Object object) {
        Class<?>[] interfaces = findInjectableClasses(object);
        injectEachClass(object, interfaces);
    }

    private void injectEachClass(Object object, Class<?>[] injectableClasses) {
        for (Class injectableClass : injectableClasses) {
            DependencyInjectors.Injector injector = injectors.injector(injectableClass);
            injector.inject(object);    //Use double dispatch to avoid this dodgy code? or is DD more of problem, gets everywhere then
        }
    }

    private Class<?>[] findInjectableClasses(Object object) {
        return object.getClass().getInterfaces();
    }

}
