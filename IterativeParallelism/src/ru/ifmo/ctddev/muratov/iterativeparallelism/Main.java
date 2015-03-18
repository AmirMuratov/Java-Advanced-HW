package ru.ifmo.ctddev.muratov.iterativeparallelism;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        IterativeParallelism p = new IterativeParallelism();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            a.add(i, i);
        }
        System.out.print(p.concat(3, a));
/*        ArrayList<Integer> mx = p.map(3, a, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        });
        for (int i = 0; i < mx.size(); i++) {
            System.out.print(mx.get(i) + "\n");
        }*/
    }
}
