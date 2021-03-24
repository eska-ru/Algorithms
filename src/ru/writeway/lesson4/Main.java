package ru.writeway.lesson4;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> mll = new MyLinkedList<>();

        System.out.print("Добавляем три элемента в начало:");
        mll.insertFirst("one");
        mll.insertFirst("two");
        mll.insertFirst("three");
        System.out.println(mll);

        System.out.print("Берём последний: ");
        System.out.println(mll.getLast());

        System.out.print("Удаляем последний: ");
        System.out.println(mll.removeLast());

        System.out.print("Берём первый: ");
        System.out.println(mll.getFirst());

        System.out.print("Удаляем первый: ");
        System.out.println(mll.removeFirst());

        System.out.print("Добавляем три элемента в конец: ");
        mll.insertLast("four");
        mll.insertLast("five");
        mll.insertLast("six");
        System.out.println(mll);

        System.out.print("Удаляем элемент one: ");
        mll.remove("one");
        System.out.println(mll);

        System.out.print("Печатаем в цикле for через итератор: ");
        for (String s : mll) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.print("Печатаем в цикле while все элементы и удаляем все элементы начиная со второго: ");
        ListIterator<String> li = mll.listIterator();
        while(li.hasNext()){
            System.out.print(li.next() + " ");
            if (li.hasPrevious()) {
                li.remove();
            }
        }
        System.out.println();
        System.out.println(mll);

        mll.insertLast("seven");
        mll.insertLast("eight");

        System.out.println("Устанавливаем итератор в конец и проходимся while'ом в обратную сторону. " +
                "После элемента с индексом 1 добавляем новый элемент:");
        li = mll.listIterator(mll.size());
        while(li.hasPrevious()){
            System.out.print(li.previous() + " ");
            if(li.previousIndex() == 1) {
                li.add("nine");
            }
        }
        System.out.println();
        System.out.println(mll);
    }
}
