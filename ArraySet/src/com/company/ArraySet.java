package com.company;

import java.util.*;

/**
 * Created by Амир on 12.03.2015.
 */
public class ArraySet<T> extends AbstractSet<T> implements NavigableSet<T> {
    private List<T> data;
    private Comparator<? super T> comparator;
    private boolean naturalOrder;

    public ArraySet(Collection<T> collection, Comparator<? super T> comparator) {
        this.comparator = comparator;
        if (collection.isEmpty()) {
            data = new ReversibleList<>(new ArrayList<>(0), false);
        } else {
            ArrayList<T> temp = new ArrayList<>(collection);
            ArrayList<T> data2 = new ArrayList<>();
            Collections.sort(temp, comparator);
            data2.add(temp.get(0));
            for (int i = 1; i < temp.size(); i++) {
                if (comparator.compare(data2.get(data2.size() - 1), temp.get(i)) != 0) {
                    data2.add(temp.get(i));
                }
            }
            data = new ReversibleList<>(data2, false);
        }
    }

    public ArraySet(Collection<T> collection) {
        this(collection, new Comparator<T>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(T o1, T o2) {
                return ((Comparable<T>) o1).compareTo(o2);
            }
        });
        naturalOrder = true;
    }

    public ArraySet() {
        this(null, true);
    }

    private ArraySet(Comparator<? super T> comparator, boolean naturalOrder) {
        this.data = new ReversibleList<>(new ArrayList<>(0), false);
        this.comparator = comparator;
        this.naturalOrder = naturalOrder;
    }

    private ArraySet(List<T> list, Comparator<? super T> comparator, boolean naturalOrder) {
        this.data = list;
        this.comparator = comparator;
        this.naturalOrder = naturalOrder;
    }

    @Override
    public T lower(T t) {
        int res = Collections.binarySearch(data, t, comparator);
        if (res < 0) {
            res = -(res + 1);
        }
        if (res > 0 && res <= data.size()) {
            return data.get(res - 1);
        }
        return null;
    }

    @Override
    public T floor(T t) {
        int res = Collections.binarySearch(data, t, comparator);
        if (res >= 0 && res < data.size()) {
            return data.get(res);
        }
        if (res < 0) {
            res = -(res + 1);
        }
        if (res > 0 && res <= data.size()) {
            return data.get(res - 1);
        }
        return null;
    }

    @Override
    public T ceiling(T t) {
        int res = Collections.binarySearch(data, t, comparator);
        if (res < 0) {
            res = -(res + 1);
        }
        if (res >= 0 && res < data.size()) {
            return data.get(res);
        }
        return null;
    }

    @Override
    public T higher(T t) {
        int res = Collections.binarySearch(data, t, comparator);
        if (res + 1 == data.size()) {
            return null;
        }
        if (res >= 0 && res + 1 < data.size()) {
            return data.get(res + 1);
        }
        if (res < 0) {
            res = -(res + 1);
        }
        if (res >= 0 && res < data.size()) {
            return data.get(res);
        }
        return null;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return Collections.binarySearch(data, (T) o, comparator) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int curIndex = 0;

            @Override
            public boolean hasNext() {
                return curIndex < data.size();
            }

            @Override
            public T next() {
                return data.get(curIndex++);
            }
        };
    }

    @Override
    public NavigableSet<T> descendingSet() {
        List<T> data2 = new ReversibleList<>(data, true);
        return new ArraySet<>(data2, comparator);
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private int curIndex = data.size();

            @Override
            public boolean hasNext() {
                return curIndex > 0;
            }

            @Override
            public T next() {
                return data.get(--curIndex);
            }
        };
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        if (fromElement == null || toElement == null) {
            throw new NullPointerException();
        }
        if (comparator.compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException();
        }

        int from = Collections.binarySearch(data, fromElement, comparator);
        int to = Collections.binarySearch(data, toElement, comparator);

        if (from >= 0 && from < data.size()) {
            if (!fromInclusive) {
                ++from;
            }
        }
        if (from < 0) {
            from = -(from + 1);
        }

        if (to >= 0 && to < data.size()) {
            if (!toInclusive) {
                --to;
            }
        }
        if (to < 0) {
            to = -(to + 1);
            --to;
        }

        return new ArraySet<>(data.subList(from, Math.max(to + 1, from)), comparator, naturalOrder);
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        if (isEmpty() || comparator.compare(first(), toElement) > 0) {
            return new ArraySet<>(comparator, naturalOrder);
        }

        return subSet(first(), true, toElement, inclusive);
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        if (isEmpty() || comparator.compare(fromElement, last()) > 0) {
            return new ArraySet<>(comparator, naturalOrder);
        }
        return subSet(fromElement, inclusive, last(), true);
    }

    @Override
    public Comparator<? super T> comparator() {
        if (naturalOrder) {
            return null;
        } else {
            return comparator;
        }
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(0);
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(data.size() - 1);
    }

    @Override
    public T pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T pollLast() {
        throw new UnsupportedOperationException();
    }

}