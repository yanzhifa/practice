package com.ldy.java8.function;

import java.util.function.Function;

/**
 * Created by yanz3 on 11/8/16.
 */
public class Student {
    public int age;
    public String gender;

    public Student(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public String customShow(Function<Student, String> fun) {
        return fun.apply(this);
    }
}
