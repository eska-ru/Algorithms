package ru.writeway.lesson4;

import java.util.EmptyStackException;

public class MyLinkedStack<T> {
    private MyLinkedList<T> ll = new MyLinkedList<>();

    public void push(T item) {
        ll.insertLast(item);
    }

    public T peek(){
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return ll.getLast();
    }

    public T pop(){
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return ll.removeLast();
    }

    public int size(){
        return ll.size();
    }

    public boolean isEmpty(){
        return ll.isEmpty();
    }
}
