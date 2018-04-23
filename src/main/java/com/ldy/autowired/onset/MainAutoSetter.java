package com.ldy.autowired.onset;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAutoSetter {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/SetBeans.xml");

        TextEditor te = (TextEditor) context.getBean("textEditor");

        te.spellCheck();
    }
}
