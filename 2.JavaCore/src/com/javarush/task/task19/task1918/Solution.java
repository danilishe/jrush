package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{

        // имя файла с консоли
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();

        String tag = args[0].toLowerCase();
        StringBuilder data = new StringBuilder();

//        StringBuilder data = new StringBuilder("<span>text1</span>\n" +
//                "<span text2>text1</span>\n" +
//                "<span\n" +
//                "text2>text1</span> Хрень которой быть не должно <spanline>Этой строки быть не должно</spanline>" +
//                "даже если тут написать <span> это будет /span целиком </span> tag между /span" +
//                "Info about Leela <span xml:lang=»en» lang=»en»><b><span>Turanga Leela\n" +
//                "</span></b></span><span>Super</span><span>girl</span>");

//                <span>text1</span>
//                <span text2>text1</span>
//                <span text2>text1</span>
//                <span> это будет /span целиком </span>
//                <span xml:lang=»en» lang=»en»><b><span>Turanga Leela </span></b></span>
//                <span>Turanga Leela </span>
//                <span>Super</span>
//                <span>girl</span>

        // считываем файл в один стрингБуфер
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while (file.ready()) data.append(file.readLine());
        file.close();

        //Зачищаем строку от спец. символов
        String clearData = data.toString().replaceAll("\\t|\\r|\\n", " ").replaceAll("\\s+", " ");

        String[] array = clearData.toString().split("<");
        for (int i = 0; i < array.length; i++) {
            if (array[i].trim().matches( tag + "\\b.*>.*")) {
                int insiderIndex = 0; // индекс вложенности
                int shift = 0; // смещение относительно текущего индекса массива
                String line = "";
                while(true) {
                    // если тег - закрывающий
                    if (array[i + shift].trim().matches("/" + tag + "\\b.*")) {
                        // уменьшаем индекс вложенности
                        insiderIndex--;
                        // Если тег закрывающий, выходим из цикла и добавляем ТОЛЬКО закрывающий тег
                        if (insiderIndex == 0) {
                            line += "<" + array[i + shift].substring(0, array[i + shift].indexOf(">") + 1);
                            System.out.println(line);
                            break;
                        } else line += "<" + array[i + shift];
                    } else { // если не закрывающий
                        // добавляем строку из массива со смещением
                        line += "<" + array[i + shift];
                        // если ещё один открывающий тег, индекс вложенности увел.
                        if (array[i + shift].trim().matches(tag + "\\b.*>.*")) insiderIndex++;
                    }
                    // если закрывающий тег, индекс вложенности уменьш.
                    shift++;
                    // увеличиваем смещение
                }
            }
        }
    }
}
