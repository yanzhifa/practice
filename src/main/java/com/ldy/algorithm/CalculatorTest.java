package com.ldy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by yanz3 on 4/28/18.
 */
public class CalculatorTest {

    public static void main(String[] args) {

        //ReadTest();

        System.out.println(Math.sqrt(2));

        System.out.println(Double.MAX_VALUE);
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(1.2);
        System.out.println(b1.multiply(b2));

    }

    public static void ReadTest() {

        while (true) {
            String name = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //拿构造的方法传到BufferedReader中
            try { //该方法中有个IOExcepiton需要捕获
                name = br.readLine();
                System.out.println("Stack:" + name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (name.equals("exit")) {
                System.exit(0);
            }
        }

    }

    public static double calculate(double a, double b, String operator) {
        double returnValue = 0;

        switch (operator) {
            case "+":
                returnValue = a + b;
                break;
            case "-":
                returnValue = a - b;
                break;
            case "*":
                returnValue = a * b;
                break;
            case "/":
                returnValue = a / b;
                break;
            case "sqrt":
                returnValue = Math.sqrt(a);
                break;
        }

        return returnValue;
    }
}
