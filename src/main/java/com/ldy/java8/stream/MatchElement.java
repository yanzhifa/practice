package com.ldy.java8.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yanz3 on 11/7/16.
 */
public class MatchElement {

    public static void main(String[] args) {
        Predicate<Integer> p = num -> num % 2 == 0;
        List<Integer> list = Arrays.asList(3, 5, 6);
        System.out.println("allMatch:" + list.stream().allMatch(p));
        System.out.println("anyMatch:" + list.stream().anyMatch(p));
        System.out.println("noneMatch:" + list.stream().noneMatch(p));

        System.out.println("=====================================");
        List<Integer> listConnection = Arrays.asList(3, 5, 6);
        int sum = listConnection.stream().collect(Collectors.summingInt(i -> i));
        System.out.println("Sum: " + sum);

        System.out.println("=====================================");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 7);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        Stream<Integer> resStream = Stream.concat(list1.stream(), list2.stream());
        resStream.forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println("=====================================");
        List<Integer> list3 = Arrays.asList(3, 4, 6);
        System.out.println("Count: " + list3.stream().filter(p).count());

        System.out.println("=====================================");
        List<Integer> list4 = Arrays.asList(3, 4, 6, 6, 4);
        list4.stream().distinct().forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println("=====================================");
        List<Integer> list5 = Arrays.asList(3, 4, 6);
        list5.stream().filter(p).forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("=====================================");
        List<String> list6 = Arrays.asList("G", "B", "F", "E");
        String any = list6.stream().findAny().get();
        System.out.println("FindAny: " + any);
        String first = list6.stream().findFirst().get();
        System.out.println("FindFirst: " + first);

        System.out.println("=====================================");
        Integer[][] data = {{1, 2}, {4, 3}, {5, 6}};
        Stream<Integer> stream = Arrays.stream(data).flatMap(row -> Arrays.stream(row));
        stream.filter(num -> num % 2 == 0)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println("=====================================");
        Integer[] data1 = {1, 2, 3, 4, 7, 6, 5};
        System.out.println("---forEach Demo---");
        Arrays.stream(data1).filter(num -> num % 2 == 1).forEach(s -> System.out.print(s + " "));
        System.out.println("\n---forEachOrdered Demo---");
        Arrays.stream(data1).filter(num -> num % 2 == 1).forEachOrdered(s -> System.out.print(s + " "));
        System.out.println();
        Stream.of("AAA", "BBB", "CCC").parallel().forEach(s -> System.out.println("Output:" + s));
        Stream.of("AAA", "BBB", "CCC").parallel().forEachOrdered(s -> System.out.println("Output:" + s));

        System.out.println("=====================================");
        String str = "Hello World!";
        Stream<String> stream1 = Stream.generate(str::toString).limit(5);
        stream1.forEach(s -> System.out.println(s));

        System.out.println("=====================================");
        Stream<Integer> stream2 = Stream.iterate(1, n -> n * 2).limit(5);
        stream2.forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println("=====================================");
        List<Integer> list7 = Arrays.asList(1, 2, 3, 4);
        list7.stream().map(i -> i * i).forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("=====================================");
        Optional<Integer> result =
                list7.stream().filter(obj -> obj == 5).findAny();
        System.out.println(result.isPresent());

        boolean find = list7.parallelStream().anyMatch(integer -> integer == 6);
        System.out.println(find);

        List<A> aList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            aList.add(new A("a" + i));
        }
//        aList.add(new A("b"));
//        aList.add(new A("c"));

//        List<A> aList1 = aList.stream().map(a -> convert(a)).collect(Collectors.toList());
//        System.out.println(aList1.size());
        System.out.println("====================================");
        System.out.println("List size: "+aList.size());
        System.out.println(System.currentTimeMillis() + "============");
        long test = aList.stream().filter(a -> a.name.equals("a1")).count();
        System.out.println(test);
        System.out.println(System.currentTimeMillis() + "============");

        System.out.println("====================================");

        System.out.println(System.currentTimeMillis() + "============");
        long test1 = aList.parallelStream().filter(a -> a.name.equals("a1")).count();
        System.out.println(test1);
        System.out.println(System.currentTimeMillis() + "============");

        System.out.println("====================================");

        System.out.println(System.currentTimeMillis() + "============");
        for(A a : aList) {
            if(a.name.equals("a1")) {
                System.out.println("a1");
            }
        }
        System.out.println(System.currentTimeMillis() + "============");


        List<String> strings = Arrays.asList("a","b",null);
        List<String> strings2 = Arrays.asList("a","b");
        Set<String> stringSet = new HashSet<>();
        for(String s : strings) {
            stringSet.add(s);
        }
//        Set<String> stringSet = strings.stream().map(s -> s).collect(Collectors.toSet());
        System.out.println(stringSet);
        System.out.println(strings.containsAll(strings2));

    }


    static class A {
        public A(String name) {
            this.name = name;
        }

        String name;

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private static A convert(A a) {
        return null;
    }

}
