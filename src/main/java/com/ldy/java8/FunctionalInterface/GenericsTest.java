package com.ldy.java8.FunctionalInterface;

import java.util.function.Function;
import java.util.function.Supplier;

public class GenericsTest {

    public static void main(String[] args) {
        //testFunction();

//        testSupplier();

        testCustomLamda();
    }

    public static void testFunction() {
        //简单的,只有一行
        Function<Integer, String> function1 = (x) -> "test result: " + x;

        //标准的,有花括号, return, 分号.
        Function<String, String> function2 = (x) -> {
            return "after function1" + x;
        };
        System.out.println();
        System.out.println(function1.apply(6));
        System.out.println(function1.andThen(function2).apply(6));
    }

    public static void testSupplier() {
        //简写
        Supplier<String> supplier1 = () -> "Test supplier";
        System.out.println(supplier1.get());

        //标准格式
        Supplier<Integer> supplier2 = () -> {
            return 20;
        };
        System.out.println(supplier2.get() instanceof Integer);
    }


    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    @FunctionalInterface
    public interface CustomLamda<T> {

        T testCustomFunction(Consumer<T> cunsumer);

        /*如果把下面方法的注释放开, 那么接口就报错了. 验证了前面所说的:@FunctionalInterface注解的接口只允许         *有一个抽象方法
         */
        //T anErrorMethod();
    }

    public static void testCustomLamda() {
        Consumer<String> consumer = (x) -> {
            System.out.println("test" + x);
        };
        CustomLamda<String> customLamda = (x) -> {
            x.accept("6");
            return "6";
        };
        System.out.println(customLamda.testCustomFunction(consumer));
    }

    public void testConsumer() {
        Consumer<String> consumer1 = (x) -> System.out.print(x);
        Consumer<String> consumer2 = (x) -> {
            System.out.println(" after consumer 1");
        };
    }

}
