package com.ldy.test;

/**
 * Created by yanz3 on 8/12/16.
 */
public class Person {
    String name;

    Integer age;

    private Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Person clone(){
        Person person = new Person(this.getName(), this.getAge());
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(1^1^2);
    }
}
