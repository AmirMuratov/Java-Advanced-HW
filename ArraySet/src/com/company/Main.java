package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Integer[] a = {2, 3, 4, 5, 1};

        ArrayList list = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        ArraySet<Integer> cc = new ArraySet<Integer>(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        ArraySet<Integer> t = (ArraySet<Integer>) cc.subSet(1, false, 5, true);
        for (Integer k : t) {
            System.out.println(k);
        }
    }
}
