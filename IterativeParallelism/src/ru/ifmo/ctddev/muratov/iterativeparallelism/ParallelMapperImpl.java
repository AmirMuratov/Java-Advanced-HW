package ru.ifmo.ctddev.muratov.iterativeparallelism;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

/**
 * IterativeParallelism was created by amir on 26.03.15.
 */
public class ParallelMapperImpl implements ParallelMapper {

    private List<Thread> threads;
    private int numOfThreads;
    private final MyBlockingQueue<ObjectToProcess> queue;

    class  ObjectToProcess<T, K> {
        int left;
        int right;
        Call<T, K> call;
        public ObjectToProcess(int left, int right, Call<T, K> call) {
            this.left = left;
            this.right = right;
            this.call = call;
        }
    }

    class MyBlockingQueue<T> {
        final Queue<T> queue;

        public MyBlockingQueue() {
            queue = new ArrayDeque<>();
        }

        void add(T element) {
            synchronized (queue) {
                queue.add(element);
                queue.notify();
            }
        }

        T pop() {
            synchronized (queue) {
                return queue.poll();
            }
        }

        boolean isEmpty() {
            synchronized (queue) {
                return queue.isEmpty();
            }
        }
    }


    class Call<T, K> {
        public List<? extends T> data;
        public Function<? super T, ? extends K> function;
        public List<K> result;
        public int elementsCounted;
        public boolean countingInterrupted;

        public Call(List<? extends T> data, Function<? super T, ? extends K> function) {
            elementsCounted = 0;
            countingInterrupted = false;
            this.data = data;
            this.function = function;
            result = new ArrayList<>(data.size());
        }

        synchronized public void incProgress(int elements) {
            elementsCounted += elements;
            if (isDone()) {
                this.notify();
            }
        }

        public boolean isDone() {
            return elementsCounted == result.size();
        }

        public void countI(int index) {
            result.set(index, function.apply(data.get(index)));
        }

        public List<K> getResult() {
            if (isDone()) {
                return result;
            } else {
                return null;
            }
        }

        public void fail() {
            countingInterrupted = true;
        }

        public boolean failed() {
            return countingInterrupted;
        }
    }
    class CountingProcess implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                ObjectToProcess element = null;
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        element = queue.pop();
                    }
                }
                if (element != null) {
                    for (int i = element.left; i <= element.right; i++) {
                        element.call.countI(i);
                        element.call.incProgress(1);
                        if (Thread.interrupted()) {
                            break;
                        }
                    }
                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public ParallelMapperImpl(int numOfThreads) {
        queue = new MyBlockingQueue<>();
        threads = new ArrayList<>(numOfThreads);
        this.numOfThreads = numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            threads.set(i, new Thread(new CountingProcess()));
        }
        threads.stream().forEach(Thread::start);
    }

    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> function, List<? extends T> list) throws InterruptedException {
        Call<T, R> currentCall = new Call<>(list, function);
         int n = Math.max(numOfThreads, list.size());
        int c = list.size() / n;
        int remainder = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                remainder = list.size() % n;
            }
            queue.add(new ObjectToProcess<>(i * c, (i + 1) * c - 1 + remainder, currentCall));
        }
        currentCall.wait();
        return currentCall.getResult();
    }

    @Override
    public void close() throws InterruptedException {
        threads.stream().forEach(Thread::interrupt);
    }
}
