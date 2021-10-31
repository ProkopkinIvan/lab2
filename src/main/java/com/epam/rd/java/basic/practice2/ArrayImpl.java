package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] items;

    private int arrayLength;

    public ArrayImpl(int size) {
        items = new Object[size];
    }

    @Override
    public void clear() {
        arrayLength = 0;
        items = new Object[arrayLength];
    }

    @Override
    public int size() {
        return arrayLength;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            boolean result = true;
            if (items.length <= currentIndex+1 || items[currentIndex+1] == null) {
                result = false;
            }

            return result;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();

            Object nextItem = items[currentIndex+1];

            currentIndex++;

            return nextItem;
        }

    }

    @Override
    public void add(Object element) {
        set(arrayLength, element);
    }

    @Override
    public void set(int index, Object element) {
        if (index > items.length - 1) {
            Object[] newArray = new Object[index+1];
            for (int i = 0; i < items.length; i++) {
                newArray[i] = items[i];
            }
            newArray[index] = element;

            items = newArray;
            arrayLength++;
        } else {
            if (items[index] == null) {
                arrayLength++;
            }
            items[index] = element;
        }
    }

    @Override
    public Object get(int index) {
        if (index > items.length - 1) {
            throw new NoSuchElementException();
        }

        return items[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < items.length; i++)
            if (element.equals(items[i]))
                return i;

        return -1;
    }

    @Override
    public void remove(int index) {
        if (index > items.length - 1) {
            throw new NoSuchElementException();
        }

        Object[] newArray = new Object[items.length - 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i > index) {
                newArray[i] = items[i+1];
            } else if (i < index) {
                newArray[i] = items[i];
            } else {
                newArray[i] = items[i+1];
            }
        }

        items = newArray;

        arrayLength--;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            result.append(items[i] == null ? "null" : items[i]);

            if ((i + 1) != items.length) {
                result.append(", ");
            }
        }

        return "[" + result.toString() + "]";
    }

    public static void main(String[] args) {
        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.add(3);
        arrayImpl.add(5);
        arrayImpl.add(2);

        System.out.println("First:");
        System.out.println(arrayImpl.get(0));
        System.out.println("Second:");
        System.out.println(arrayImpl.get(1));
        System.out.println("Third:");
        System.out.println(arrayImpl.get(2));
        System.out.println("Size:");
        System.out.println(arrayImpl.size());
        System.out.println("Index of 2:");
        System.out.println(arrayImpl.indexOf(2));

        System.out.println("toString:");
        System.out.println(arrayImpl);

        arrayImpl.set(2, "new 2");

        System.out.println("After set 2:");
        System.out.println(arrayImpl);

        System.out.println("After set 2:");
        System.out.println(arrayImpl);

        System.out.println("After remove second element:");
        arrayImpl.remove(1);
        System.out.println(arrayImpl);

        System.out.println("Size:");
        System.out.println(arrayImpl.size());

        arrayImpl.clear();
        System.out.println("Size after clear:");
        System.out.println(arrayImpl.size());
        System.out.println("toString after clear:");
        System.out.println(arrayImpl);
    }

}