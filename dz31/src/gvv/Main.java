package gvv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        TestCl obj = new TestCl();
        Class<?> cls = obj.getClass();

        Method[] methods = cls.getMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(Test.class)){
                Test test = method.getAnnotation(Test.class);
                int sMethod = (Integer)method.invoke(obj, test.par1(), test.par2());
                System.out.println(sMethod);
            }
        }
    }
}
