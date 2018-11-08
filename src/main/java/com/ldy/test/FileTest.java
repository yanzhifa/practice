package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FileTest<K, V extends Object> {

    public static <K, V> FileTest<K, V> as() {
        return new FileTest<>();
    }

    public static <T,E> E showKeyName(T t, E e){
        return e;
    }


    public static void main(String[] args) {

        File file = new File("/Users/yanzhifa/code/practice/target/classes/XML.xml");
        System.out.println(file.getAbsolutePath());

        System.out.println(StringUtils.substringAfter("etst@123.com", "@"));

        System.out.println(Integer.valueOf("56257930") / 2 == 0);
        System.out.println(56257930 % 2 );

        FileTest<String, String> f = FileTest.<String, String>as();

        f.test("a","b");

    }

    private void test(K k, V v) {
        System.out.println(k + "" + v);
    }
}
