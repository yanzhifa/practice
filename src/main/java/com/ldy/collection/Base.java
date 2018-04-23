package com.ldy.collection;

/**
 * Created by yanz3 on 1/12/17.
 */
public class Base {

    private String item1;

    private String item2;

    public Base(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    @Override
    public String toString() {
        return "Base{" +
                "item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                '}';
    }
}
