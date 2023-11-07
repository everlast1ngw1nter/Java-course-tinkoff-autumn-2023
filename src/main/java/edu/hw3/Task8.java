package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Task8 {

    private Task8() {
    }

    public static class BackwardIterator<T> implements Iterator<T> {

        private final T[] array;

        private int position;

        public BackwardIterator(Collection<T> collection) {
            array = (T[]) collection.toArray();
            position = array.length;
        }

        @Override
        public boolean hasNext() {
            return position != 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            position--;
            return array[position];
        }
    }
}
