package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;

/**
 * Created by bakla410 on 15.12.17.
 */
public interface Actionable {
    void processAction(Action action, SensorEvent event);
}
