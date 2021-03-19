package ru.writeway.lesson3;

import org.jetbrains.annotations.NotNull;

public class StringInverter {
    private StringInverter(){}

    public static String invert(@NotNull String str) {
        MyStack<Character> stack = new MyStack<>(str.length());
        for (Character ch : str.toCharArray()) {
            stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

}
