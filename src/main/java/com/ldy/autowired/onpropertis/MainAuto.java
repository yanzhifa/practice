package com.ldy.autowired.onpropertis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAuto {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/PropertiesBeans.xml");

        TextEditor te = (TextEditor) context.getBean("textEditor");

        te.spellCheck();
    }
}
