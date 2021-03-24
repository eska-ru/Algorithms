package ru.writeway.lesson4;

public class MyLinkedQueue<T> {
    private MyLinkedList<T> ll = new MyLinkedList<>();

    public void insert(T item) {
        ll.insertFirst(item);
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return ll.getLast();
    }

    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return ll.removeLast();
    }

    public boolean isEmpty() {
        return ll.isEmpty();
    }

    public int size() {
        return ll.size();
    }
}
