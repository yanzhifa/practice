package com.ldy.java8;

public interface SimpleInterface {
    public void doSomeWork();

    // A default method in the interface created using "default" keyword
    // 使用default关键字创在interface中直接创建一个default方法，该方法包含了具体的实现代码
    default public void doSomeOtherWork() {
        System.out.println("DoSomeOtherWork implementation in the interface");
    }
}
