package com.ldy.algorithm;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by yanz3 on 4/28/18.
 */
public class RPNTest {

    public static void main(String[] args) throws IOException {
        String[] tokens = new String[]{"2", "2", "3", "*", "4", "+"};


        System.out.println(evalRPN(tokens));

        System.out.println(Math.sqrt(9.3));

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(10);

        for (int i = 0; i < 20; i++) {
            if (arrayDeque.size() == 10) {
                arrayDeque.removeFirst();
                arrayDeque.offer(i);
            } else if (arrayDeque.size() == 5) {
                arrayDeque.removeLast();
            } else {
                arrayDeque.add(i);
            }
            //System.out.println(arrayDeque);
            //System.out.println("---" + arrayDeque.getLast());

        }
        arrayDeque.forEach(element-> System.out.println(element));
    }

    static Integer HISTORY_STACK_SIZE = 10;
    static Stack<Double> currentStack = new Stack<>();
    static ArrayDeque<Stack<Double>> historyDeque = new ArrayDeque<>(HISTORY_STACK_SIZE);

    public static Stack<Double> evalRPN(String[] input) {

        List<String> calcOperators = Arrays.asList("+", "-", "*", "/", "sqrt", "undo", "clear");
        List<String> otherOperation = Arrays.asList("undo", "clear");

        for (String value : input) {
            if (!calcOperators.contains(value)) {
                currentStack.push(Double.valueOf(value));
                makeRecord(currentStack, false);
            } else {
                double first = Double.valueOf(currentStack.pop());
                double second = Double.valueOf(currentStack.pop());
                switch (value) {
                    case "+":
                        currentStack.push(first + second);
                        break;
                    case "-":
                        currentStack.push(second - first);
                        break;
                    case "*":
                        currentStack.push(first * second);
                        break;
                    case "/":
                        currentStack.push(second / first);
                        break;
                    case "sqrt":
                        currentStack.push(second);
                        currentStack.push(Math.sqrt(second));
                        break;
                    case "undo":
                        //TODO
                        currentStack = historyDeque.getLast();
                        makeRecord(currentStack, true);
                        break;
                    case "clear":
                        currentStack.clear();
                        break;
                }
            }
        }
        return currentStack;
    }


    public static void makeRecord(Stack<Double> stack, boolean erase) {
        Stack<Double> element = (Stack<Double>) stack.clone();
        if (erase) {
            historyDeque.removeLast();
        } else {
            if (historyDeque.size() == HISTORY_STACK_SIZE) {
                historyDeque.removeFirst();
            }
            historyDeque.offer(element);
        }
    }
}

