package com.company;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * Created by Амир on 12.03.2015.
 */
public class ReversibleList<T> extends AbstractList<T> implements RandomAccess {
    private List<T> data;

    private boolean reversed;

    ReversibleList(List<T> list, boolean reversed) {
        data = list;
        this.reversed = reversed;
    }

    @Override
    public T get(int index) {
        if (!reversed) {
            return data.get(index);
        } else {
            return data.get(data.size() - index - 1);
        }
    }

    @Override
    public int size() {
        return data.size();
    }

}
