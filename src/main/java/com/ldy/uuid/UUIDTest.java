package com.ldy.uuid;


import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());

        System.out.println(UUID.nameUUIDFromBytes("test".getBytes()));
        System.out.println(UUID.nameUUIDFromBytes("test".getBytes()));
    }
}
