package uk.co.rossbeazley.avp.android.application;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class DependenciesServiceTest {

    @Test
    public void testInjectDependenciesDefinedByInterface() throws Exception {

        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry()
                .register(InjectableSomeClass.class, new SomeClassInjector());
        DependenciesService ds = new DependenciesService(injectorRegistry);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(object.isInjected(), is(true));
    }

    @Test
    public void testInjectDependenciesDefinedByInterfaceNotFound() throws Exception {

        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry();
        DependenciesService ds = new DependenciesService(injectorRegistry);
        SomeClass object = new SomeClass();
        ds.injectDependencies(object);

        assertThat(true, is(true));
    }

    @Test
    public void testDependenciesDefinedOnSuperclassInjected() {

        DependanciesInjectorRegistry injectorRegistry = new DependanciesInjectorRegistry()
                .register(InjectableSomeClass.class, new SomeClassInjector())
            .register(InjectableSuperClass.class, new SomeSuperClassInjector());

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

    private final class SomeClassInjector implements DependenciesService.Injector<InjectableSomeClass> {
        @Override
        public void inject(InjectableSomeClass object) {
            object.setInjected(true);
        }
    }

    private final class SomeClass extends SomeSuperClass implements InjectableSomeClass {
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


    private final class SomeSuperClassInjector implements DependenciesService.Injector<InjectableSuperClass> {
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



