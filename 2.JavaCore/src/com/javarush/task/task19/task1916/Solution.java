package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws Exception{
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = console.readLine(),
             file2Name = console.readLine();
        console.close();

//        file1Name = "c:/tmp/1.txt";
//        file2Name = "c:/tmp/2.txt";

        BufferedReader file1 = new BufferedReader(new FileReader(file1Name));
        List<String> file1List = new ArrayList<>();
        while (file1.ready()) {
            file1List.add(file1.readLine());
        }
        file1.close();

        BufferedReader file2 = new BufferedReader(new FileReader(file2Name));
        List<String> file2List = new ArrayList<>();
        while (file2.ready()) {
            file2List.add(file2.readLine());
        }
        file2.close();

        boolean listNotFilled = true;

        while (listNotFilled) {
            String current1Line = file1List.get(0),
                    current2Line = file2List.get(0);
            String next2Line;
                    try {
                        next2Line = file2List.get(1);
                    } catch (IndexOutOfBoundsException e) {
                        next2Line = "";
                    }
            if ( current1Line.equals(current2Line) ) {
                lines.add(new LineItem(Type.SAME, current1Line));
                file1List.remove(0);
                file2List.remove(0);
            }
            else if ( current1Line.equals(next2Line) ) {
                lines.add(new LineItem(Type.ADDED, current2Line));
                file2List.remove(0);
            }
            else {
                lines.add(new LineItem(Type.REMOVED, current1Line));
                file1List.remove(0);
            }
            if ((file1List.size() == 0) ||
                    (file2List.size() == 0)) listNotFilled = false;
        }

        if (file1List.size() > 0)
            for (int i = 0; i < file1List.size(); i++) {
                lines.add(new LineItem(Type.REMOVED, file1List.get(i)));
            }

        if (file2List.size() > 0)
            for (int i = 0; i < file2List.size(); i++) {
                lines.add(new LineItem(Type.ADDED, file2List.get(i)));
            }

//        for (LineItem lineItem :
//                lines) {
//            System.out.println( lineItem.type + ": "+ lineItem.line);
//        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
