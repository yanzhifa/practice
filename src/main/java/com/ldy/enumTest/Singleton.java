package com.ldy.enumTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanz3 on 8/26/16.
 */
public enum Singleton {
    INSTANCE;

    // instance vars, constructor
    private final String connection;

    private static List<String> list = new ArrayList<>();

    Singleton() {
        // Initialize the connection
        connection = "connect to server";
        System.out.println("singleton");
    }

    static {
        list.add("aaa");
        list.add("bbb");
    }

    // Static getter
    public static Singleton getInstance() {
        return INSTANCE;
    }

    public String getConnection() {
        return connection;
    }

    public static List<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getList());
    }
}
