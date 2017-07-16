package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods someInterfaceWithMethods = new SomeInterfaceWithMethodsImpl();

        ClassLoader cl = someInterfaceWithMethods.getClass().getClassLoader();
        Class<?>[] interfaces = someInterfaceWithMethods.getClass().getInterfaces();
        System.out.println(cl);
        System.out.println(Arrays.asList(interfaces));
        CustomInvocationHandler handler = new CustomInvocationHandler(someInterfaceWithMethods);
        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(cl, interfaces, handler);
    }
}