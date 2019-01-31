package com.ldy.time;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(Clock.systemUTC());
        LocalDateTime localDateTime = LocalDateTime.now(Clock.systemUTC());

        System.out.println(Clock.systemUTC().instant());
        System.out.println(Clock.systemUTC().millis());
        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(localDate.plusMonths(1).withDayOfMonth(1));

        String[] strings = {null, null};
        System.out.println(strings.length);

        YearMonth date = YearMonth.from(localDate);
        System.out.println(date.toString());


        System.out.println(TimeUnit.DAYS.toMillis(1));

        m1("aa");

        Map map = null;
        System.out.println();

        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(2017, 1, 9,
                10, 30, 20),
                ZoneId.of("GMT"));
        System.out.println(zdt.toInstant().toEpochMilli());

        final Date currentTime = new Date();

        final SimpleDateFormat sdf =
                new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss z");

        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm:ss").withZone(ZoneId.of("GMT"));

        // Give it to me in GMT time.
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT time: " + sdf.format(currentTime));
        System.out.println("GMT time: " + timeColonFormatter.format(localDateTime));

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
        System.out.println(ZonedDateTime.now().format(FORMATTER));

        int i = 2;
        if(i!=3 && i!=4) {
            System.out.println("aa");
            System.out.println(String.format("%s%s%s", "aaa", "bbb", "ccc"));
        }

        System.out.println(String.format("C-%s-%s-%02d-%02d-%s",
                "aa",
                "bb",
                localDate.getMonthValue(),
                localDate.getYear(),
                "ee"));
    }

    private static void m1(String str1, String... str2) {
        System.out.println(str1 + ":" + str2.length);

    }
}
