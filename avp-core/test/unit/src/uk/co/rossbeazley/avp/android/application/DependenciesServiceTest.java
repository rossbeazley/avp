package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DependenciesServiceTest {

    @Test
    public void testInjectDependenciesDefinedByInterface() throws Exception {

        DependencyInjectorMap injectors = new DependencyInjectorMap(){{
            register(InjectableSomeClass.class, new SomeClassInjector());
        }};
        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry(injectors);
        DependenciesService ds = new DependenciesService(injectorRegistry);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isInjected(), is(true));
    }

    @Test
    public void testInjectDependenciesDefinedByInterfaceNotFound() throws Exception {

        DependencyInjectorMap injectors = new DependencyInjectorMap();
        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry(injectors);
        DependenciesService ds = new DependenciesService(injectorRegistry);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(true, is(true));
    }

    @Test
    public void testDependenciesDefinedOnSuperclassInjected() {

        DependencyInjectorMap injectors = new DependencyInjectorMap(){{
            register(InjectableSomeClass.class, new SomeClassInjector());
            register(InjectableSuperClass.class, new SomeSuperClassInjector());
        }};

        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry(injectors);
        DependenciesService ds = new DependenciesService(injectorRegistry);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isSuperInjected(), is(true));
    }

    @Test
    public void getsClassesFromClassWithCorrectClasses() {
        assertThat(getInterfaces(SomeClass.class).length,is(2));
    }

    private Class<?>[] getInterfaces(Class<?> someClassClass) {
        ArrayList<Class<?>> rtn = new ArrayList();

        Class<?> clazz = someClassClass;

        for(Class<?>[] interfaces = clazz.getInterfaces() ; clazz.getSuperclass()!=null ; clazz = clazz.getSuperclass(),interfaces = clazz.getInterfaces()) {
            rtn.addAll(Arrays.asList(interfaces));
        }

        return rtn.toArray(new Class[rtn.size()]);
    }

    private class SomeClassInjector implements DependencyInjectorMap.Injector<InjectableSomeClass> {
        @Override
        public void inject(InjectableSomeClass object) {
            object.setInjected(true);
        }
    }

    private class SomeClass extends SomeSuperClass implements InjectableSomeClass {
        private boolean injected = false;

        private boolean isInjected() {
            return injected;
        }

        public void setInjected(boolean injected) {
            this.injected = injected;
        }
    }

    interface InjectableSomeClass {
        public void setInjected(boolean injected);
    }


    private class SomeSuperClassInjector implements DependencyInjectorMap.Injector<InjectableSuperClass> {
        @Override
        public void inject(InjectableSuperClass object) {
            object.setSuperInjected(true);
        }
    }

    private class SomeSuperClass implements InjectableSuperClass{
        private boolean superInjected;

        @Override
        public void setSuperInjected(boolean injected) {
            superInjected = injected;
        }

        public boolean isSuperInjected() {
            return superInjected;
        }
    }

    interface InjectableSuperClass {
        public void setSuperInjected(boolean injected);
    }

}



