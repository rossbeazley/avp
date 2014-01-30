package uk.co.rossbeazley.avp.android.application;

public class DependenciesService {
    private final DependencyInjectors injectors;

    public DependenciesService(DependencyInjectors injectors) {
        this.injectors = injectors;
    }

    void injectDependencies(Object fragment) {
        DependencyInjectors.Injector injector = injectors.injector(fragment.getClass());
        injector.inject(fragment);    //Use double dispatch to avoid this dodgy code? or is DD more of problem, gets everywhere then
    }

}
