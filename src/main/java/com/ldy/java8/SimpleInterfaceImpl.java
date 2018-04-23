package com.ldy.java8;

import com.sun.istack.internal.NotNull;

class SimpleInterfaceImpl implements SimpleInterface {
    @Override
    public void doSomeWork() {
        System.out.println("Do Some Work implementation in the class");
    }
    /*
     * Not required to override to provide an implementation 
     * for doSomeOtherWork.
     * 在SimpleInterfaceImpl里，不需要再去实现接口中定义的doSomeOtherWork方法
     */

    @Override
    public void doSomeOtherWork() {
        System.out.println("asdfasdf");
    }

    public void doThings(@NotNull String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        SimpleInterfaceImpl simpObj = new SimpleInterfaceImpl();
        simpObj.doSomeWork();
        simpObj.doSomeOtherWork();
        simpObj.doThings(null);
    }
}
