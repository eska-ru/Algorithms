package ru.writeway.lesson3;

import java.util.NoSuchElementException;

public class MyDeque<T> {
    private T[] list;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private int begin;
    private int end;

    public MyDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public MyDeque() {
        this(DEFAULT_CAPACITY);
    }

    public void offerLast(T item) {
        if (isFull()) {
            reCapacity(capacity * 2);
        }
        size++;
        list[end] = item;
        end = nextIndex(end);
    }

    public void offerFirst(T item) {
        if (isFull()) {
            reCapacity(capacity * 2);
        }
        size++;
        begin = previousIndex(begin);
        list[begin] = item;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[begin];
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[end-1];
    }

    public T pollFirst() {
        T temp = peekFirst();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return temp;
    }

    public T pollLast() {
        T temp = peekLast();
        size--;
        list[end] = null;
        end = previousIndex(end);
        return temp;
    }

    public boolean isFull() {
        return size == list.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }

    private int previousIndex(int index) {
        return (list.length + index - 1) % list.length;
    }

    private void reCapacity(int newCapacity){
        if (newCapacity <= capacity) {
            throw new IllegalArgumentException("newCapacity: " + capacity);
        }

        T[] tempArr = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            tempArr[i] = list[(i + begin) % list.length];
        }

        list = tempArr;
        capacity = newCapacity;
        begin = 0;
        end = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = begin; i < (begin+size); ++i) {
            sb.append(list[i % list.length]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }
}
