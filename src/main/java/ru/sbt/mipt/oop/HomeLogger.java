package ru.sbt.mipt.oop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bakla410 on 16.12.17.
 */
@Aspect
public class HomeLogger {

    @Autowired
    private Logger logger;

    @AfterReturning(pointcut = "execution(* ru.sbt.mipt.oop.EventExecutors.EventListener.listen(..))", returning = "returnValue")
    public void eventsLog(JoinPoint point, SensorEvent returnValue) {
        if (returnValue != null) {
            logger.info("Event received: " + returnValue.toString());
        }
    }

    @Before("execution(* ru.sbt.mipt.oop.EventExecutors.EventProcessor.processEvent(..))")
    public void doorLog(JoinPoint point) {
        logger.info("Process event for: " + point.getArgs()[1].toString());
    }

    @Before("execution(* ru.sbt.mipt.oop.Main.start(..))")
    public void beforeLog(JoinPoint point) {
        logger.info("Main app started to listen for events");
    }

    @After("execution(* ru.sbt.mipt.oop.Main.start(..))")
    public void afterLog(JoinPoint point) {
        logger.info("Main app stopped listening for events");
    }
}
