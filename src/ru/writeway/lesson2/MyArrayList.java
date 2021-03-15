package ru.writeway.lesson2;

public class MyArrayList<T extends Comparable<T>> {
    private T[] list;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0 -- capacity is " + capacity);
        }
        this.capacity = capacity;
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    private void checkMaxSize() {
        if (size == Integer.MAX_VALUE) {
            throw new ArrayStoreException();
        }
    }

    private void preCheckLoadFactor() {
        if (capacity * 0.75 < (size+1)) {
            createBiggerArray();
        }
    }

    private void createBiggerArray() {
        long longCapacity = 2 * (long) capacity;
        if (longCapacity > Integer.MAX_VALUE) {
            longCapacity = Integer.MAX_VALUE;
        }
        capacity = (int) longCapacity;

        T[] newArr = (T[]) new Comparable[capacity];
        for (int i = 0; i < size; ++i) {
            newArr[i] = list[i];
        }

        list = newArr;
    }

    private void checkIndexIsValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(T item) {
        checkMaxSize();
        preCheckLoadFactor();

        list[size] = item;
        size++;
    }

    public void add(int index, T item) {
        checkMaxSize();
        preCheckLoadFactor();
        checkIndexIsValid(index);

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public final T remove(int index) {
        checkIndexIsValid(index);

        T temp = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        list[size] = null;
        return temp;
    }

    public boolean remove(T item) {
        int i = indexOf(item);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        checkIndexIsValid(index);

        return list[index];
    }

    public void set(int index, T item) {
        checkIndexIsValid(index);

        list[index] = item;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }

        sb.append(" ]");
        return sb.toString();
    }

    private boolean less(T item1, T item2) {
        if (item1 == null) {
            throw new NullPointerException();
        }

        return item1.compareTo(item2) < 0;
    }

    private void swap(int index1, int index2) {
        checkIndexIsValid(index1);
        checkIndexIsValid(index2);

        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void selectionSort() {
        int iMin;
        for (int i = 0; i < size - 1; i++) {
            iMin = i;
            for (int j = i + 1; j < size; j++) {
                if (less(list[j], list[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
    }

    public void insertionSort() {
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];
            while (j > 0 && less(key, list[j - 1])) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
    }

    public void bubbleSort() {
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                }
            }
        }
    }

    public void bubbleSortO() {
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (less(list[j + 1], list[j])) {
                    swap(j + 1, j);
                    isSwap = true;
                }
            }
            if( !isSwap){
                break;
            }
        }
    }
}
