package ru.writeway.lesson3;

public class StringInverter {
    private StringInverter(){}

    public static String invert(String str) {
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
