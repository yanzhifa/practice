package com.ldy.test;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by yanz3 on 4/17/17.
 */
public class StaticTest1 {



    public static void main(String[] args) {
        method1();
        System.out.println(StringUtils.equals(null, null));
    }

    private static void method1() {

        try {
            System.out.println("1");
            problem();
        } catch (RuntimeException ex) {
            System.out.println("2");
            return;
        } catch (Exception e) {
            System.out.println("3");
            return;
        } finally {
            System.out.println("4");
        }
        System.out.println("5");
    }

    private static void problem() throws Exception {
        throw new Exception();
    }


}
