package com.ldy.lombok;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Getter
@Setter
@Slf4j
public class Account {

    public Account(String name, String address) {
        this.name = name;
        this.address = address;
    }

    private String name;

    private String address;

    @ToString.Exclude
    private boolean deleted = false;

    public static void main(String[] args) {

        Account account = new Account("joe", "east road");
        System.out.println(account.toString());

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }

        try {

            System.out.println(1/0);
        } catch (Exception e) {
            log.error("error ecour because: {}","1/0",e);
        }
    }
}
