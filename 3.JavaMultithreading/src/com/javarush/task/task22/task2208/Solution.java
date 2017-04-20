package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
//        Map<String, String> testMap = new HashMap<>();
//        testMap.put("name", "Ivanov");
//        testMap.put("country", "Ukraine");
//        testMap.put("city", "Kiev");
//        testMap.put("age", null);
//
//        System.out.println(getQuery(testMap));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder line = new StringBuilder();
        int count = 0;
        for (Map.Entry map :
                params.entrySet()) {
            if ((map.getKey() != null) && (map.getValue() != null)) {
                if (count++ > 0) line.append(" and ");
                line.append(map.getKey() + " = " + '\'' + map.getValue() + '\'');
            }
        }
        return line.toString();
    }
}
