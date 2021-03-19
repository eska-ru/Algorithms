package ru.writeway.lesson3;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private T[] list;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    private int begin;
    private int end;

    public MyQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public MyQueue() {
        this(DEFAULT_CAPACITY);
    }

    public void insert(T item) throws IllegalStateException {
        if (isFull()) {
            reCapacity(capacity * 2);
        }
        size++;
        list[end] = item;
        end = nextIndex(end);
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[begin];
    }

    public T remove() {
        T temp = peekFront();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
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
