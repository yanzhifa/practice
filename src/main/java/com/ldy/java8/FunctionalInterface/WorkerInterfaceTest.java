package com.ldy.java8.FunctionalInterface;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yanz3 on 5/8/18.
 */

//定义一个函数式接口
@FunctionalInterface
interface WorkerInterface {
    public void doSomeWork();
}

public class WorkerInterfaceTest {

    public static void execute(WorkerInterface worker) {
        worker.doSomeWork();
    }

    public static void main(String[] args) {

        //invoke doSomeWork using Annonymous class
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Anonymous class");
            }
        });

        //invoke doSomeWork using Lambda expression
        execute(() -> System.out.println("Worker invoked using Lambda expression"));
    }
}
