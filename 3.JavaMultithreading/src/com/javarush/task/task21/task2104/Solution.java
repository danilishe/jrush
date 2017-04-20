package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    //http://info.javarush.ru/Coder/2015/09/23/%D0%9F%D0%B5%D1%80%D0%B5%D0%B3%D1%80%D1%83%D0%B7%D0%BA%D0%B0-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4%D0%BE%D0%B2-equals-%D0%B8-hashCode-%D0%B2-Java.html
    @Override
    public boolean equals(Object n) {

        if (n == null) return false;
        if (n.getClass() != getClass()) return false;
        if (this == n) return true;
        Solution other = (Solution) n;
        if ((other.first == null) || (other.last == null)) return false;
        if ( !other.first.equals(first) || !other.last.equals(last) ) return false;

        return true;
    }
    @Override
    public int hashCode() {
        if (this != null) return 31 * first.hashCode() + last.hashCode();
        else return 0;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));}
}
