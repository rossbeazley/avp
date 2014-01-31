package uk.co.rossbeazley.avp.android.application;

import java.util.HashMap;
import java.util.Map;

public class DependencyInjectors {

    private final Map<Class, Injector> injectorsByTargetClass = new HashMap<Class, Injector>();

    public <I> void register(Class<I> cl, Injector<I> clInjector) {
        injectorsByTargetClass.put(cl, clInjector);
    }

    public <I> Injector<I> injector(Class<I> cl) {
        return injectorsByTargetClass.get(cl);
    }

    public interface Injector<T> {
        void inject(T object);
    }
}