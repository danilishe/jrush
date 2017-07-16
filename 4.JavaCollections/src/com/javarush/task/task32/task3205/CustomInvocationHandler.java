package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Данил on 18.06.2017.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods siwm;
    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods) {
        siwm = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("" + method.getName() + " in");
        Object result = method.invoke(siwm, args);
        System.out.println("" + method.getName() + " out");
        return result;
    }
}
