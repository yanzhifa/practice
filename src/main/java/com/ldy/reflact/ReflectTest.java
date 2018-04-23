package com.ldy.reflact;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;

public class ReflectTest {
    public static void main(String[] args) {
        ReflectTest test = new ReflectTest();
        Person person = test.new Person("jack", 20);
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(person.getClass());
        for (final PropertyDescriptor pd : pds) {
            if (pd.getPropertyType().getName().equalsIgnoreCase("java.lang.Class")) {
                continue;
            }

            Method readM = pd.getReadMethod();
            Method writeM = pd.getWriteMethod();

            System.out.println(readM.getDefaultValue());
            System.out.println(readM.getName() + ":" + writeM.getName());
        }

    }

    class Person {
        String name;
        Integer age;

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

    }
}
