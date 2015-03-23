package ru.ifmo.ctddev.muratov.iterativeparallelism;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        IterativeParallelism p = new IterativeParallelism();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        a.add(23);
        //System.out.print(p.concat(3, a) + "\n");

        Integer mx = p.minimum(3, a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o2 == null) {
                    return 1;
                }
                if (o1 == null) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        });
        System.out.print(mx);

    }
}
