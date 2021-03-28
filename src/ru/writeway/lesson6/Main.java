package ru.writeway.lesson6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int nTrue = 0, nFalse = 0;

        Random random = new Random();
        Integer last = 0;
        for (int i = 0; i < 200000; i++) {
            MyTreeMap<Integer, String> map = new MyTreeMap<>();
            while (map.height() < 7) {
                last = random.nextInt(100)-200;
                map.put(last, "String");
            }
            map.delete(last);
            if (map.isBalanced()) {
                nTrue++;
            } else {
                nFalse++;
            }
        }

        System.out.println(nTrue + " " + nFalse);
    }
}
