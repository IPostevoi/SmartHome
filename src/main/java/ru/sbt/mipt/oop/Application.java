package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String... args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        ((Main) context.getBean("main")).start();
    }
}
