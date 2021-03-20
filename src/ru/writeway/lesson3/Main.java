package ru.writeway.lesson3;

public class Main {
    public static void main(String[] args) {
        System.out.println(StringInverter.invert("привет этот угрюмый мир"));
        System.out.println(StringInverter.invert("а роза упала на лапу азора"));

        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.insert(2);
        queue.insert(3);
        System.out.println(queue);
        queue.insert(4);
        System.out.println(queue);
        queue.insert(5);
        System.out.println(queue);

        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.offerLast(5);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        System.out.println(deque);
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        System.out.println(deque.pollFirst() + " " + deque);
        System.out.println(deque.pollLast() + " " + deque);
        deque.pollLast();
        deque.pollLast();
        System.out.println(deque);
    }
}
