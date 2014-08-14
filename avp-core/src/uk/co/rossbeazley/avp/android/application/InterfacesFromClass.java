package uk.co.rossbeazley.avp.android.application;

import java.util.ArrayList;
import java.util.Arrays;

final class InterfacesFromClass {
    private Object objectForInjection;

    public InterfacesFromClass(Object objectForInjection) {
        this.objectForInjection = objectForInjection;
    }

    public Class<?>[] invoke() {
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
