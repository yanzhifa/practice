package com.ldy.json;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yanz3 on 7/25/17.
 */
public class Test {

    private static Type type;


    public static void test(Map<Integer, String> map){
        Map<Integer, String> m = new HashMap<>();
        m.put(2,"b");
        map = m;
//        map.put(3,"c");
        System.out.println(map);
    }



    public static void main(String[] args) {
        Type[] types = Type.values();
        System.out.println(Arrays.toString(types));

        List<Type> list = Arrays.asList(types);
        System.out.println(list);

        String[] enumNames = Stream.of(Type.values())
                .map(Enum::name)
                .toArray(String[]::new);

        List<String> stringList = Arrays.asList(Arrays.toString(types));
        System.out.println(stringList);

        System.out.println(Type.MANAGEMENT.name() == "MANAGEMENT");
        if(Arrays.stream(Type.values()).filter(type -> type.name() == "B").count() == 0) {
            System.out.println("asdf");
        }

        System.out.println("10.62.92.228\n".replace("\\n",""));

        Map<Integer, String> map = new HashMap<>();
        map.put(1,"a");
        test(map);
        System.out.println(map);
    }
}
