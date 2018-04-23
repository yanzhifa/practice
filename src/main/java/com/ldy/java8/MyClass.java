package com.ldy.java8;

public class MyClass implements InterfaceB {
    public static void main(String[] args) {
        MyClass mc = new MyClass();
        mc.sayHi();
    }

//    @Override
//    public void saySomething() {
//        // TODO Auto-generated method stub
//    }

//    @Override
//    public void sayHi() {
//        InterfaceA.super.sayHi();
//        System.out.println("implemetation of sayHi() in MyClass");
//    }
}

interface InterfaceA {
    public void saySomething();

    default public void sayHi1() {
        System.out.println("Hi from InterfaceA");
    }
}

interface InterfaceB {
    default public void sayHi() {
        //System.out.println("Hi from InterfaceB");
    }
}
