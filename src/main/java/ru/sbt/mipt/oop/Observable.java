package ru.sbt.mipt.oop;

/**
 * Created by user7 on 28.10.2017.
 */
public interface Observable {

    void add(Observer observer);

    void remove(Observer observer);

    void notifyObservers(SmartHome smartHome, SensorEvent event);

}
