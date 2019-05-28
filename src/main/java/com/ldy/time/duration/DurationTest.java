package com.ldy.time.duration;

import java.time.Duration;
import java.time.Period;

public class DurationTest {

    public static void main(String[] args) {

        System.out.println(Duration.ofHours(30));
        System.out.println(Duration.ofDays(2));
        Duration duration = Duration.ofMinutes(0);
        System.out.println(String.valueOf(duration));
        System.out.println(Duration.ofMinutes(3600));

        System.out.println(Period.ofDays(2));
        System.out.println(Period.ofMonths(2));
        System.out.println(Period.ofWeeks(2));
        System.out.println(Period.ofYears(2));
    }
}
