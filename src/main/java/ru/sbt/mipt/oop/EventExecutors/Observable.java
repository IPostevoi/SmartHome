package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.SmartHome;

/**
 * Created by user7 on 28.10.2017.
 */
public interface Observable {

    void add(Observer observer);

    void remove(Observer observer);

    void notifyObservers(SmartHome smartHome, SensorEvent event);

}
