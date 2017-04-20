package com.javarush.task.task25.task2510;

/**
 * Created by danilishe on 06.04.2017.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//            1. Если это Error, то вывести в консоль «Нельзя дальше работать«.
//            2. Если это Exception, то вывести в консоль «Надо обработать«.
//            3. Если это Throwable, то вывести в консоль «ХЗ«.

        if (e.getClass() == Error.class) System.out.println("Нельзя дальше работать");
        if (e.getClass() == Throwable.class) System.out.println("ХЗ");
        if (e.getClass() == Exception.class) System.out.println("Надо обработать.");
    }

}
