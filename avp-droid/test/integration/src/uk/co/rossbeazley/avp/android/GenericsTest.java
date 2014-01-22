package uk.co.rossbeazley.avp.android;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GenericsTest {

    private final AGenericClass<Integer> integer = new AGenericClass<Integer>();
    private final AGenericClass<Float> floatingpoint = new AGenericClass<Float>();

    private final HashMap< Class<?> , AGenericClass> map = new HashMap< Class<?> , AGenericClass>() {
        {
            put(integer.gClass(), integer);
            put(floatingpoint.gClass(), floatingpoint);
        }
    };

    @Test
    public void getInteger() {
        AGenericClass<Integer> actual = map.get(integer.getClass().getCanonicalName());
        assertThat(actual, is(integer));
    }

    @Test
    public void getFloat() {
        AGenericClass<Float> actual1 = map.get(floatingpoint.getClass().getCanonicalName());
        assertThat(actual1, is(floatingpoint));
    }

    public static class AGenericClass<T> {
            public Class<T> gClass() {

                Class<T> cls;
                Type genericSuperclass = AGenericClass.class.getGenericSuperclass();
                ParameterizedType genericSuperclass1 = (ParameterizedType) genericSuperclass;
                cls = (Class<T>) genericSuperclass1.getActualTypeArguments()[0];
                T t = null;
                try {
                    t = cls.getConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                return cls;
            }
    }
}
