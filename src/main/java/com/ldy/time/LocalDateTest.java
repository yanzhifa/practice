package com.ldy.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(Clock.systemUTC());

        System.out.println(Clock.systemUTC().instant());
        System.out.println(Clock.systemUTC().millis());
        System.out.println(localDate);

        String[] strings = {null, null};
        System.out.println(strings.length);

        YearMonth date = YearMonth.from(localDate);
        System.out.println(date.toString());


        System.out.println(TimeUnit.DAYS.toMillis(1));

        m1("aa");

        Map map = null;
        System.out.println();
    }

    private static void m1(String str1, String... str2) {
        System.out.println(str1 +":"+ str2.length);

    }
}
