package com.ldy.time;

import java.time.LocalDate;

public class LocalDateDemo {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();

        LocalDate firstDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1).minusMonths(1);
        LocalDate lastDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1).minusDays(1);

        System.out.println(firstDay +":"+lastDay);
    }
}
