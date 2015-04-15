package ru.ifmo.ctddev.muratov.iterativeparallelism;

import info.kgeorgiy.java.advanced.concurrent.ListIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * created by amir on 18.03.15.
 */
public class IterativeParallelism implements ListIP {

    boolean hasMapper;
    ParallelMapper mapper;

    public IterativeParallelism(ParallelMapper mapper) {
        hasMapper = true;
        this.mapper = mapper;
    }

    public IterativeParallelism() {
        hasMapper = false;
    }

    private class RunAndSave<T, K> implements Runnable {

        private T dataArray;
        private K result;
        private Function<T, K> function;

        public RunAndSave(T data, Function<T, K> function) {
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


    private <T, K> void countInParallelThreads(List<RunAndSave<T, K>> parallel) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        parallel.stream().map(Thread::new).forEach(threads::add);
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private <T, K> List<K> count(int n, List<? extends T> list, Function<List<? extends T>, K> function) throws InterruptedException {
        List<RunAndSave<List<? extends T>, K>> parallelCounting = new ArrayList<>(n);
        n = Math.min(n, list.size());
        int c = list.size() / n;
        int r = 0;
        ArrayList<List<? extends T>> listForMapper = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                r = list.size() % n;
            }
            if (hasMapper) {
                listForMapper.add(list.subList(c * i, c * (i + 1) + r));
            } else {
                parallelCounting.add(i, new RunAndSave<>(list.subList(c * i, c * (i + 1) + r), function));
            }
        }
        if (hasMapper) {
            return mapper.map(function, listForMapper);
        } else {
            countInParallelThreads(parallelCounting);
            return parallelCounting.stream().map(RunAndSave::returnResult).collect(Collectors.toList());
        }
    }

    /**
     * Finds first maximum in given list. Splits given list into n lists, and finds maximum in each list in
     * separate thread and them merges results.
     * @param n number of threads
     * @param list list to find maximum
     * @param comparator Comparator to compare elements of this list
     * @return element of list, which is the maximum, according comparator
     * @throws InterruptedException
     */

    @Override
    public <T> T maximum(int n, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, T> max = data -> data.stream().max(comparator).get();
        List<T> result = count(n, list, max);
        return max.apply(result);
    }

    /**
     * Finds first minimum in given list. Splits given list into n lists, and finds minimum in each list in
     * separate thread and them merges results.
     * @param n number of threads
     * @param list list to find minimum
     * @param comparator Comparator to compare elements of this list
     * @return element of list, which is the minimum, according comparator
     * @throws InterruptedException
     */


    @Override
    public <T> T minimum(int n, List<? extends T> list, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, T> min = data -> data.stream().min(comparator).get();
        List<T> result = count(n, list, min);
        return min.apply(result);
    }

    /**
     * Checks, if all elements of list applies predicate. Splits list into n lists, and
     * checks elements in each list in separate thread and them merges results.
     * @param n - number of threads
     * @param list - list to check
     * @param predicate - predicate to apply to elements of this list
     * @return true, if all elements applies predicate. Returns false otherwise.
     * @throws InterruptedException
     */

    @Override
    public <T> boolean all(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> all = data -> data.stream().allMatch(predicate);
        List<Boolean> result = count(n, list, all);
        return result.stream().allMatch(Predicate.isEqual(true));
    }

    /**
     * Checks, whether any elements of given list match the provided predicate. Splits list
     * into n lists, and checks elements in each list in separate thread and them merges results.
     * @param n - number of threads
     * @param list - list to check
     * @param predicate - predicate to apply to elements of this list
     * @return true if any elements of the list match the provided predicate, otherwise false
     * @throws InterruptedException
     */

    @Override
    public <T> boolean any(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> any = data -> data.stream().anyMatch(predicate);
        List<Boolean> result = count(n, list, any);
        return result.stream().anyMatch(Predicate.isEqual(true));
    }


    private <T> List<T>  expandList(List<List<? extends T>> list) {
        List<T> result = new ArrayList<>();
        list.forEach(x -> x.forEach(result::add));
        return result;
    }

    /**
     * Returns a stream consisting of the elements of this stream that match the given predicate. Splits list
     * into n lists, and filters elements in each list in separate thread and them merges results.
     * @param n - number of threads
     * @param list - list to filter
     * @param predicate - predicate to apply to elements of this list
     * @return the new list, which contains applied by predicate elements
     * @throws InterruptedException
     */


    @Override
    public <T> List<T> filter(int n, List<? extends T> list, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, List<? extends T>> filter = data -> {
            List<T> result = new ArrayList<>();
            data.stream().filter(predicate).forEach(result::add);
            return result;
        };
        return expandList(count(n, list, filter));
    }

    /**
     * Returns a list consisting of the results of applying given function to the elements of list. Splits list
     * into n lists, and applies function to elements in each list in separate thread and them merges results.
     * @param n - number of threads
     * @param list - list to map
     * @param function - function to apply to each element
     * @return the new list, which contains applied by function elements
     * @throws InterruptedException
     */

    @Override
    public <T, U> List<U> map(int n, List<? extends T> list, Function<? super T, ? extends U> function) throws InterruptedException {
        Function<List<? extends T>, List<? extends U>> map = data -> {
            List<U> result = new ArrayList<>();
            data.stream().map(function).forEach(result::add);
            return result;
        };
        return expandList(count(n, list, map));
    }

    /**
     * Applies toString to every element of list and returns result of concatination of those strings. Splits list
     * into n lists, and do this stuff for each list in separate thread and them merges results.
     * Uses {@link java.lang.StringBuilder} to concatinate strings.
     * @param n - number of threads
     * @param list - list to concatinate
     * @return the concatination of string representation of elements of list
     * @throws InterruptedException
     * @see java.lang.StringBuilder
     */

    @Override
    public String concat(int n, List<?> list) throws InterruptedException {
        Function<List<?>, String> concat = data -> {
            StringBuilder result = new StringBuilder();
            data.stream().map(Object::toString).forEach(result::append);
            return result.toString();
        };
        List<String> result = count(n, list, concat);
        return concat.apply(result);
    }
}
