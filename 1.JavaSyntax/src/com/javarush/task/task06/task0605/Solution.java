package com.javarush.task.task06.task0605;


import java.io.*;

/* 
Контролируем массу тела
«Недовес: меньше чем 18.5» — если индекс массы тела меньше 18.5,
«Нормальный: между 18.5 и 24.9» — если индекс массы тела между 18.5 и 24.9,
«Избыточный вес: между 25 и 29.9» — если индекс массы тела между 25 и 29.9,
«Ожирение: 30 или больше» — если индекс массы тела 30 или больше.
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        double weight = Double.parseDouble(bis.readLine());
        double height = Double.parseDouble(bis.readLine());

        Body.massIndex(weight, height);
    }

    public static class Body {
        public static void massIndex(double weight, double height) {
            Double imt = weight / (height * height); //напишите тут ваш код
            if (imt < 18.5) System.out.println("Недовес: меньше чем 18.5");
            if ((imt >= 18.5) && (imt <= 24.9)) System.out.println("Нормальный: между 18.5 и 24.9");
            if ((imt >= 25) && (imt <= 29.9)) System.out.println("Избыточный вес: между 25 и 29.9");
            if (imt >= 30) System.out.println("Ожирение: 30 или больше");
        }
    }
}
