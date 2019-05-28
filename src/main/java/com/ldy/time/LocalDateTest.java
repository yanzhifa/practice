package com.ldy.time;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LocalDateTest {

    public static void main(String[] args) {
        long masterDataSyncInterval = 365 * 24 * 3600 * 1000L;
        long a = 365 * 24 *1000*360L;
        System.out.println(masterDataSyncInterval);
        System.out.println(Long.MAX_VALUE);

        LocalDate localDate = LocalDate.now(Clock.systemUTC());
        LocalDateTime localDateTime = LocalDateTime.now(Clock.systemUTC());

        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());
        System.out.println(OffsetDateTime.now(Clock.system(ZoneId.of("UTC+2"))));
        System.out.println(ZonedDateTime.now());
        System.out.println("------------------------");
        System.out.println(Clock.systemUTC().instant());
        System.out.println(Clock.systemUTC().millis());
        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(localDate.plusMonths(1).withDayOfMonth(1));

        System.out.println("------------------");
        System.out.println(localDate.plusDays(10).getMonth() == localDate.getMonth());
        System.out.println(localDate.plusDays(10).withDayOfMonth(1));
        System.out.println("------------------");

        String[] strings = {null, null};
        System.out.println(strings.length);

        YearMonth date = YearMonth.from(localDate);
        System.out.println(date.toString());
        System.out.println(YearMonth.of(date.getYear(),date.getMonth().minus(1)));
        System.out.println(date.isAfter(YearMonth.of(date.getYear(),date.getMonth().minus(1))));


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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(OffsetDateTime.parse("2018-12-19T00:00:00.000+00:00").toLocalDate() + "----------");

        System.out.println(OffsetDateTime.parse("2018-12-19T00:00:00.000+01:00").withOffsetSameInstant(ZoneOffset.UTC).toLocalDate() + "----------");

        System.out.println(OffsetDateTime.parse("2018-12-19T00:00:00.000+08:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC))+ "----------");
        System.out.println(OffsetDateTime.parse("2018-12-19T00:00:00.000+08:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC)).toLocalDate()+ "----------");
        System.out.println(OffsetDateTime.parse(OffsetDateTime.now().toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC))+ "----------");

        System.out.println(ZonedDateTime.parse("2016-08-17T22:00:00+02:00").withZoneSameInstant(ZoneId.of("UTC")));
        System.out.println(ZonedDateTime.parse("2018-12-19T00:00:00.000+01:00").withZoneSameInstant(ZoneId.of("UTC")));

        OffsetDateTime utc = OffsetDateTime.now();
        System.out.println(utc);
        LocalDateTime localDateTime1 = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println(localDateTime1);


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

    private static void updateClock() {
        LocalDate effectiveDate = LocalDate.now(Clock.systemUTC()).plusDays(10);
        LocalDateTime localDateTime2 = LocalDateTime.of(effectiveDate, LocalTime.now(Clock.systemUTC()));
        long offset = localDateTime2.toInstant(ZoneOffset.UTC).toEpochMilli() - System.currentTimeMillis();
        Clock.offset(Clock.systemUTC(), Duration.ofMillis(offset));
    }
}
