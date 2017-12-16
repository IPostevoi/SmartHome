package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.enums.SensorEventType;

/**
 * Created by user7 on 14.10.2017.
 */
public class EventListenerImpl implements EventListener {

    @Override
    public SensorEvent listen() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
