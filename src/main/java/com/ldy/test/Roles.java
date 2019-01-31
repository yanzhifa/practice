package com.ldy.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Roles {

    public static final String[][] mapping = new String[][] {
            {"a","aa", "aaa"},
            {"b","bb", "bbb"},
    };

    public static void main(String[] args) {
        System.out.println(mapping);

        System.out.println(LocalDate.now().minusDays(3));

        List<String> stringList = Arrays.asList("aaa", null, "ccc");

        List<String> s = stringList.stream()
                .map(str->{if(str!=null) str = str+"1"; return str;})
                .collect(Collectors.toList());

        System.out.println(s);

        BigDecimal b1 = new BigDecimal(100);
        System.out.println(b1.compareTo(BigDecimal.valueOf(100))>=0);
    }
}
