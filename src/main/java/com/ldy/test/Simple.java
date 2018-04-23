package com.ldy.test;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yanz3 on 9/23/16.
 */
public class Simple {
    public static void main(String[] args) {
        String oldName = "sdfsdf";
        oldName.split("@");
        String name = StringUtils.substring(oldName, 0, oldName.indexOf("@")+1);
        System.out.println(oldName.split("@")[0]);

        Son son = new Son();
        if(son instanceof Simple) {
            System.out.println("1");
        }
        son.setAge("1");
        String oldAge = son.getAge();

        son.setAge("2");
        System.out.println(son);
        System.out.println(oldAge);

        String s = "asdf/";
        System.out.println(s.substring(1));


    }
}

class Son extends Simple {
    String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Son{" +
                "age='" + age + '\'' +
                '}';
    }
}
