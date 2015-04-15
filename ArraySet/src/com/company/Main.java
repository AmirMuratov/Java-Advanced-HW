package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Integer[] a = {2, 3, 4, 5, 1};
        ArrayList list = new ArrayList<Integer>();
        Collections.addAll(list, a);
        ArraySet<Integer> cc = new ArraySet<>(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });


        System.out.println(cc.contains(1));
        System.out.println(cc.contains(7));
        System.out.println(cc.first());
        System.out.println(cc.last());
        System.out.println(cc.lower(2));
        System.out.println(cc.ceiling(5));

        System.out.println();
        ArraySet<Integer> cc2 = (ArraySet<Integer>) cc.subSet(1, false, 5, false);
        cc2 = (ArraySet<Integer>) cc2.descendingSet();
        for (Iterator t = cc2.iterator(); t.hasNext();) {
            System.out.print(t.next());
        }
        System.out.println();
        System.out.println(cc2.contains(1));
        System.out.println(cc2.contains(7));
        System.out.println(cc2.first());
        System.out.println(cc2.last());
        System.out.println(cc2.lower(2));
        System.out.println(cc2.ceiling(5));

    }
}
