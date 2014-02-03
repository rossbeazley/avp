package uk.co.rossbeazley.avp.android.application;

public class DependenciesService {
    private final DependencyInjectors injectors;

    public DependenciesService(DependencyInjectors injectors) {
        this.injectors = injectors;
    }

    @SuppressWarnings("unchecked")
    void injectDependencies(Object object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        for (Class interfaceClass : interfaces) {
            DependencyInjectors.Injector injector = injectors.injector(interfaceClass);
            injector.inject(object);    //Use double dispatch to avoid this dodgy code? or is DD more of problem, gets everywhere then
        }
    }

}
