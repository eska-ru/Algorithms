package ru.writeway.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(calcDegree(2, 4));

        List<Item> items = new ArrayList<>(Arrays.asList(
                new Item(18, 1000),
                new Item(4, 30),
                new Item(10, 50),
                new Item(2, 60),
                new Item(7, 20),
                new Item(5, 75)
        ));

        System.out.println(calcOptimal(items, 0, 15));
    }

    public static long calcDegree(int val, int degree) {
        if (degree == 0) {
            return 1;
        }

        if (degree < 0) {
            throw new IllegalArgumentException();
        }

        if (degree == 1) {
            return val;
        }

        return calcDegree(val, degree - 1) * val;
    }

    static class Item {
        final int weight;
        final int price;

        public Item(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    private static int calcOptimal(List<Item> items, int i, int weight) {
        if (i >= items.size()) {
            return 0;
        }
        if (items.get(i).weight > weight) {
            return calcOptimal(items,i+1, weight);
        } else {
            int next = calcOptimal(items,i+1, weight);
            int current = calcOptimal(items, i+1, weight - items.get(i).weight) + items.get(i).price;
            return Math.max(current, next);
        }
    }
}
