package ru.ifmo.ctddev.muratov.iterativeparallelism;

import info.kgeorgiy.java.advanced.concurrent.ListIP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by amir on 18.03.15.
 */
public class IterativeParallelism implements ListIP {


    private class RunAndSave<T, K> implements Runnable {

        List<? extends T> dataArray;
        K result;
        Function<List<? extends T>, K> function;

        public RunAndSave(List<? extends T> data, Function<List<? extends T>, K> function) {
            dataArray = data;
            this.function = function;
        }

        @Override
        public void run() {
            result = function.apply(dataArray);
        }

        public K returnResult() {
            return result;
        }
    }


    private void parallelizer(List<RunAndSave> parallel) {
        List<Thread> threads = new ArrayList<>();
        parallel.stream().map(Thread::new).forEach(threads::add);
        threads.forEach(Thread::start);
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException ignored) {
        }

    }


    private <T, K> List<K> count(int n, List<? extends T> list, Function<List<? extends T>, K> function) {
        List<RunAndSave> parallelCounting = new ArrayList<>(n);
        int c = list.size() / n;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                r = list.size() % n;
            }
            parallelCounting.add(i, new RunAndSave<T, K>(list.subList(c * i, c * (i + 1) + r), function));
        }
        parallelizer(parallelCounting);
        List<K> result = new ArrayList<K>();
        parallelCounting.stream().forEach(x -> result.add((K) x.returnResult()));
        return result;
    }


    @Override
    public <T> T maximum(int n, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, T> max = data -> data.stream().max(comparator).get();
        List<T> result = count(n, list, max);
        return (T) result.stream().max(comparator).get();
    }

    @Override
    public <T> T minimum(int n, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, T> min = data -> data.stream().min(comparator).get();
        List<T> result = count(n, list, min);
        return (T) result.stream().min(comparator).get();
    }

    @Override
    public <T> boolean all(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> all = data -> data.stream().allMatch(predicate);
        List<Boolean> result = count(n, list, all);
        return result.stream().reduce(true, (a, b) -> a & b);
    }

    @Override
    public <T> boolean any(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> any = data -> data.stream().anyMatch(predicate);
        List<Boolean> result = count(n, list, any);
        return result.stream().reduce(false, (a, b) -> a | b);
    }

    @Override
    public <T> List<T> filter(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, List<? extends T>> filter = data -> {
            List<T> result = new ArrayList<>();
            data.stream().filter(predicate).forEach(result::add);
            return result;
        };
        List<List<? extends T>> result = count(n, list, filter);
        List<T> ans = new ArrayList<>();
        result.forEach(x -> x.forEach(ans::add));
        return ans;
    }

    @Override
    public <T, U> List<U> map(int n, List<? extends T> list, Function<? super T, ? extends U> function) throws InterruptedException {
        Function<List<? extends T>, List<? extends U>> map = data -> {
            List<U> result = new ArrayList<>();
            data.stream().map(function).forEach(result::add);
            return result;
        };
        List<List<? extends U>> result = count(n, list, map);
        List<U> ans = new ArrayList<>();
        result.forEach(x -> x.forEach(ans::add));
        return ans;
    }

    @Override
    public String concat(int n, List<?> list) throws InterruptedException {
        Function<List<?>, String> map = data -> {
            StringBuilder result = new StringBuilder();
            data.stream().map(Object::toString).forEach(result::append);
            return result.toString();
        };
        List<String> result = count(n, list, map);
        StringBuilder ans = new StringBuilder();
        result.forEach(ans::append);
        return ans.toString();
    }
}
