package com.ldy.test;

/**
 * Created by yanz3 on 8/12/16.
 */
public class Persons {
    Person boy;
    Person girl;


    public Persons() {
    }

    public Persons(Person boy, Person girl) {
        this.boy = boy;
        this.girl = girl;
    }

    public Person getBoy() {
        return boy;
    }

    public void setBoy(Person boy) {
        this.boy = boy;
    }

    public Person getGirl() {
        return girl;
    }

    public void setGirl(Person girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "boy=" + boy +
                ", girl=" + girl +
                '}';
    }
}
