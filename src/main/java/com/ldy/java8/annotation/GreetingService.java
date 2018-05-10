package com.ldy.java8.annotation;

/**
 * Created by yanz3 on 5/7/18.
 */
@FunctionalInterface
public interface GreetingService {
    void sayMessage(String message);

    default void sayHello(String hello) {

    }
}

interface Greeting extends GreetingService {
    @Override
    void sayMessage(String message);

    void hello();
}

class GreetingImpl implements Greeting {

    @Override
    public void sayMessage(String message) {
        System.out.println("Hi," + message);
    }

    @Override
    public void hello() {
        System.out.println("Hello world." );
        this.sayMessage("hello.");
    }

    public static void main(String[] args) {
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("nihao..");

        Greeting greeting = new GreetingImpl();
        greeting.hello();
    }
}
