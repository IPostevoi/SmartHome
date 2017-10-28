package ru.sbt.mipt.oop;

/**
 * Created by user7 on 14.10.2017.
 */
public interface EventProcessor {

    void processEvent(SmartHome home, SensorEvent event);
}
