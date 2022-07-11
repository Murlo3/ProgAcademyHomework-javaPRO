package gvv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
	TextContainer one = new TextContainer();
    Class<?> cls = one.getClass();

    SaveTo saveTo = cls.getAnnotation(SaveTo.class);
    String path = saveTo.path();
    Method[] methods = cls.getDeclaredMethods();
        for (Method sa: methods) {
            if (sa.isAnnotationPresent(Saver.class)){
                sa.invoke(one, path);
                break;
            }
        }

    }
}
