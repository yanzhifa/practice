package com.ldy.test;

/**
 * Created by yanz3 on 12/19/16.
 */
public class TryCatchTest {

    private static int count;

    private static void test() {
        try {
            System.out.println("try");
            throw new Exception();
        } catch(Exception e) {
            count ++;
            if(count < 1) {
                return;
            }
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        for (int i= 0;i<3; i++) {
            test();
        }
    }

}
