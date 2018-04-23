package com.ldy.file;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yanz3 on 6/18/17.
 */
public class Model {

    private String address;

    private int age;


    private Account account;

    private Account host;

    public Account getHost() {
        return host;
    }

    public void setHost(Account host) {
        this.host = host;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Model{" +
                "address='" + address + '\'' +
                ", age=" + age +
                ", account=" + account +
                ", host=" + host +
                '}';
    }
}
