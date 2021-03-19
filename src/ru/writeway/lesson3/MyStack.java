package ru.writeway.lesson3;

import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] list;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;

    public MyStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Object[capacity];
    }

    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T item) {
        if (isFull()) {
            reCapacity(capacity * 2);
        }
        list[size]= item;
        size++;
    }

    public T pop(){
        T temp = peek();
        size--;
        list[size]= null;
        return temp;
    }

    public T peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return list[size-1];
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

    private void reCapacity(int newCapacity){
        if (newCapacity <= capacity) {
            throw new IllegalArgumentException("newCapacity: " + capacity);
        }

        T[] tempArr = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, tempArr, 0, size);
        list = tempArr;
        capacity = newCapacity;
    }
}
