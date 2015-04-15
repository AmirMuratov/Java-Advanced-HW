package ru.ifmo.ctddev.muratov.iterativeparallelism;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        try (ParallelMapperImpl a = new ParallelMapperImpl(8)) {
            IterativeParallelism it = new IterativeParallelism(a);
            List<Double> b = new ArrayList<>();
            b.add(23.);b.add(11.);b.add(49.);
            String result = it.concat(1, b);
            System.out.print(result);
        }
    }
}
