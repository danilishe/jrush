package com.javarush.task.task08.task0818;

import java.util.*;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> employee = new HashMap<>();
        employee.put("some 0" , 600);
        employee.put("some 1" , 550);
        employee.put("some 2" , 500);
        employee.put("some 3" , 400);
        employee.put("some 4" , 550);
        employee.put("some 5" , 300);
        employee.put("some 6" , 150);
        employee.put("some 7" , 600);
        employee.put("some 8" , 600);
        employee.put("some 9" , 600);
        //напишите тут ваш код
        return employee;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> emp:
        map.entrySet()){
            if (emp.getValue() < 500) list.add(emp.getKey());
        }
        for (String name :
                list) {
            map.remove(name);
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
    }
}