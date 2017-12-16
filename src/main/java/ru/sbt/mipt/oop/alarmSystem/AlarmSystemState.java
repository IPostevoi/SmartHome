package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.SensorEvent;

public interface AlarmSystemState {

    AlarmSystemState turnOn();

    AlarmSystemState turnOff();

    AlarmSystemState onSensorEvent(SensorEvent sensorEvent);

    AlarmSystemState enterPassword(String password, String secret);

    AlarmStateEnum getState();

}

