package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class SlashTest {

    private Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String str1 = "abc$";
        System.out.println(str1);

        boolean val = true ^ false;
        System.out.println(true | false);

        System.out.println(System.getProperty("java.io.tmpdir"));

        System.out.println(File.separator);

        String[] s = new String[]{"111", "222", "333"};

        System.out.println(Arrays.toString(s));
        List<String> l = new ArrayList<>();
        l.add("aaaaaa");
        l.add("bbbbbb");
        String[] s1 = (String[]) l.toArray(new String[l.size()]);
        System.out.println(s1);


        System.out.println(String.join(",", l));

        String a = "a,b";
        List list = Arrays.asList(a.split("\\|"));
        System.out.println(list.size());

        SlashTest test = new SlashTest();
        test.set.add("jone");
        System.out.println(test.getClass().getSimpleName());

        try {
            Field fields = test.getClass().getDeclaredField("set");
            System.out.println(fields.isAccessible());
            fields.setAccessible(true);
            Set obj = (HashSet) fields.get(test);
            System.out.println(obj);
            obj.add("mary");
            System.out.println(obj);

            fields.set(test, obj);
            System.out.println(test.set);

        } finally {

        }

    }

}
