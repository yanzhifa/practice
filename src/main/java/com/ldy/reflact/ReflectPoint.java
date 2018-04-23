package com.ldy.reflact;

/**
 * Created by yanz3 on 1/31/17.
 */
public class ReflectPoint {
    public int x = 2345;
    private int y;
    private String str1 = "hello";

    public String getStr1() {
        return str1;
    }

    private String str2 = "yelow";
    private String str3 = "binbin";

    public ReflectPoint(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public ReflectPoint() {
        super();
    }

    @Override
    public String toString() {
        return "ReflectPoint{" +
                "x=" + x +
                ", y=" + y +
                ", str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                ", str3='" + str3 + '\'' +
                '}';
    }
}
