package ru.writeway.lesson4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;

    private int size;

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    public ListIterator<T> listIterator(int index) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }

        ListIter li = new ListIter();
        li.index = index;
        if (index == size()) {
            li.current = new Node(last, null, null);
        } else {
            for (int i = 0; i < index; i++) {
                li.next();
            }
        }
        return li;
    }

    private class Iter implements Iterator<T> {
        Node current = new Node(null, first);

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            if (current.next == null) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.value;
        }
    }

    private enum LastIteratorOperation {
        next,
        previous,
        remove,
        add,
        none
    }

    private class ListIter extends Iter implements ListIterator<T> {
        int index = 0;

        LastIteratorOperation operation = LastIteratorOperation.none;

        @Override
        public T next() {
            super.next();
            index++;
            operation = LastIteratorOperation.next;
            return current.value;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != null;
        }

        @Override
        public T previous() {
            current = current.prev;
            index--;
            operation = LastIteratorOperation.previous;
            return current.value;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index;
        }

        //удаляет элемент который прошли методом next или prev
        @Override
        public void remove() {
            if (operation != LastIteratorOperation.previous
            && operation != LastIteratorOperation.next) {
                throw new IllegalStateException();
            }

            if (current.prev == null) {
                removeFirst();
            } else if (current.next == null) {
                current = new Node(current.prev, null, null);
                removeLast();
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
            }

            operation = LastIteratorOperation.remove;
        }

        //удаляет элемент который прошли методом next или prev
        @Override
        public void set(T t) {
            if (operation != LastIteratorOperation.previous
                    && operation != LastIteratorOperation.next) {
                throw new IllegalStateException();
            }

            current.value = t;
        }

        //добавить элемент после элемента который прошли методом next или prev
        // в направлении куда шли.
        @Override
        public void add(T t) {
            if (current.prev == null) {
                insertFirst(t);
            } else if (current.next == null) {
                insertLast(t);
            } else {
                if (operation == LastIteratorOperation.next) {
                    Node node = new Node(current.prev, t, current);
                    current.prev.next = node;
                    current.prev = node;
                    index++;
                } else if (operation == LastIteratorOperation.previous) {
                    Node node = new Node(current, t, current.next);
                    current.next.prev = node;
                    current.next = node;
                } else {
                    throw new IllegalStateException();
                }
                size++;
            }
            operation = LastIteratorOperation.add;
        }
    }

    private class Node {
        Node prev;
        T value;
        Node next;

        public Node(Node prev, T value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public void insertFirst(T item) {
        Node newNode = new Node(item, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        if (first != null)
        System.out.println(first.prev + " " + first.next);
        first = newNode;
        size++;
    }

    public T removeFirst() {
        T temp = getFirst();
        first = first.next;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return temp;
    }

    public void insertLast(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public T removeLast() {
        T temp = getLast();
        if (last.prev == null) {
            first = null;
        } else {
            last.prev.next = null;
        }
        last = last.prev;
        size--;
        return temp;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        return first.value;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        return last.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public final int indexOf(T item) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("index: " + index);
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }

        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        Node newNode = new Node(current, item, current.next);
        current.next.prev = newNode;
        current.next = newNode;
        size++;
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }
        if (first.value.equals(item)) {
            removeFirst();
            return true;
        }

        Node current = first;
        while (current != null && !current.value.equals(item)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = first;
        while (current != null) {
            sb.append(current.value).append(", ");
            current = current.next;
        }
        if (!isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
