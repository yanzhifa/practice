package com.ldy.autowired.onconstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAuto {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/ConstructorBeans.xml");

        TextEditor te = (TextEditor) context.getBean("textEditor");

        te.spellCheck();
    }
}
