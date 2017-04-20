package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Почитать в инете про медиану выборки
*/
public class Solution {


    public static void main(String[] args) {
        System.out.println(Arrays.asList(sort(new Integer[] {13, 10, 15, 5, 17, 18})));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        float mediana;
        float centr = array.length / 2;
        if (array.length % 2 > 0) {
            mediana = array[ (int) centr];
        } else {
            mediana = (array[(int) centr] + array[(int) centr -1]) /2;
        }

        System.out.println(mediana + " " + centr);

        Arrays.sort(array, (Integer o1, Integer o2) -> (Math.abs((int) mediana - o1) - Math.abs((int) mediana - o2)));

        Arrays.asList(array).forEach(System.out::println);

        return array;
    }
}
