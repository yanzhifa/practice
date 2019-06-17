package com.ldy.collection;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultiMapTest {

    public static void main(String[] args) {
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();

        map.put("one", "A");
        System.out.println(map);

        map.putAll("one", Arrays.asList("B", "C"));
        System.out.println(map);

        map.put("one", "D");
        System.out.println(map);

        map.putAll("two", Arrays.asList("1", "2", "3"));
        System.out.println(map);

        System.out.printf("The value of the one key: %s\n", map.get("one"));

        try(FileWriter file = new FileWriter("/Users/yanzhifa/values")) {
            file.write(map.toString());
            System.out.println("Generate APIKey for all partners in file : " + map);
        } catch (IOException e) {
            System.out.println("Generate APIKey " + e.getMessage());
        }

        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();
        map1.put("a", "aaa");
        map1.put("b", "bbb");

        map2.put("c", "aaa");
        map2.put("d", "aaa");

        map3.putAll(map1);
        System.out.println(map3);
        map3.putAll(map2);
        System.out.println(map3);
    }
}
