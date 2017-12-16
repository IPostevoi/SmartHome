package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.SmartHome;

/**
 * Created by user7 on 14.10.2017.
 */
public interface EventProcessor {

    void processEvent(SmartHome home, SensorEvent event);
}
