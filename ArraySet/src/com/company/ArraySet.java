package com.company;

import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * Created by Амир on 23.02.2015.
 */
public class ArraySet<T> extends AbstractSet<T> implements NavigableSet<T>  {

    private List<T> data;
    private Comparator<? super T> comparator;
    private boolean naturalOrder;

    public ArraySet() {
        this(Collections.EMPTY_LIST);
    }

    public ArraySet(Collection<? extends T> data) {
        this(data, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable<T>) o1).compareTo(o2);
            }
        });
        naturalOrder = true;
    }

    public ArraySet(Collection<? extends T> collection, Comparator<? super T> comparator) {
        ArrayList<T> temp = new ArrayList<T>(collection);
        Collections.sort(temp, comparator);
        data = new ArrayList<T>();
        if (collection.size() != 0) {
            data.add(temp.get(0));
        }
        for (T t : temp) {
            if (comparator.compare(data.get(data.size() - 1), t) != 0) {
                data.add(t);
            }
        }
        this.comparator = comparator;
        naturalOrder = false;
    }

    private ArraySet(List<T> collection, Comparator<? super T> comparator, boolean order) {
        this.comparator = comparator;
        this.naturalOrder = order;
        this.data = collection;
    }

    private int searchT(T t) {
        return Collections.binarySearch(data, t, comparator);
    }

    @Override
    public T lower(T t) {
        int search = searchT(t);
        if (search == -1 || search == 0) {
            return null;
        }
        if (search < 0) {
            search = -(search + 1);
        }
        return data.get(search - 1);
    }

    @Override
    public T floor(T t) {
        int search = searchT(t);
        if (search == -1) {
            return null;
        }
        if (search >= 0) {
            return data.get(search);
        } else {
            return data.get(-(search + 1) - 1);
        }
    }

    @Override
    public T ceiling(T t) {
        int search = searchT(t);
        if (search < 0) {
            search = -(search + 1);
        }
        if (search == data.size()) {
            return null;
        }
        return data.get(search);
    }

    @Override
    public T higher(T t) {
        int search = searchT(t);
        if (search == data.size() - 1 || search == -data.size() - 1) {
            return null;
        }
        if (search < 0) {
            return data.get(-(search + 1));
        } else {
            return data.get(search + 1);
        }
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (searchT((T) o) >= 0) {
            return true;
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cur = 0;

            @Override
            public boolean hasNext() {
                return cur != data.size();
            }

            @Override
            public T next() {
                if (cur == data.size()) {
                    return null;
                }
                return data.get(cur++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        for (int i = 0; i < Math.min(data.size(), a.length); i++) {
            a[i] = (T1) data.get(i);
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object t : c) {
            if (!contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public NavigableSet<T> descendingSet() {
        ArraySet<T> set = new ArraySet<T>();
        for (int i = data.size() - 1; i > 0; i--) {
            set.data.add(data.get(i));
        }
        set.comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return -comparator.compare(o1, o2);
            }
        };
        return set;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            int cur = data.size() - 1;

            @Override
            public boolean hasNext() {
                return cur != -1;
            }

            @Override
            public T next() {
                if (cur == -1) {
                    return null;
                }
                return data.get(cur--);
            }
        };
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        int from = searchT(fromElement);
        int to = searchT(toElement);
        if (from >= 0 && !fromInclusive) {
            from++;
        }
        if (to >= 0 && !toInclusive) {
            to--;
        }
        if (from < 0) {
            from = -(from + 1);
        }
        if (to < 0) {
            to = -(to + 1) - 1;
        }
        return new ArraySet<T>(data.subList(from, to + 1), comparator, naturalOrder);
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        return subSet(this.first(), true, toElement, inclusive);
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return subSet(fromElement, inclusive, this.last(), true);
    }

    @Override
    public Comparator<? super T> comparator() {
        if (naturalOrder) {
            return null;
        }
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return subSet(fromElement, true, toElement, true);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return subSet(this.first(), true, toElement, true);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return subSet(fromElement, true, this.last(), true);
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
}
