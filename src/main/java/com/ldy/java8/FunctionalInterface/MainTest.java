package com.ldy.java8.FunctionalInterface;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yanz3 on 7/9/17.
 */
public class MainTest {

    @FunctionalInterface
    public interface MyRunnable {
        public abstract void run() throws Exception;
    }

    private static void invokeRun(MyRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static Set<String> testRun(List<String> stringList) {
        Set<String> volumes = new LinkedHashSet<String>();
        stringList.parallelStream().forEach(
                string -> {
                    invokeRun(() -> {
                        try {
                            if (string.contains("a")) {
                                System.out.println("contains a ...." + string);
                                volumes.add(string);
                            } else {
                                System.out.println("not contains a ...." + string);
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("not contain a");
                            volumes.add("ERROR");
                        }

                        Thread.sleep(3000);
                    });
                }
        );
        return volumes;
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("haha", "jay", "hello");
        Set<String> set = testRun(list);
        System.out.println(set);

    }
}
