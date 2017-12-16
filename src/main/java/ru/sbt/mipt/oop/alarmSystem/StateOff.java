package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.SensorEvent;

public class StateOff implements AlarmSystemState {

    @Override
    public AlarmSystemState turnOn() {
        return new StateWait(this);
    }

    @Override
    public AlarmSystemState turnOff() {
        return this;
    }

    @Override
    public AlarmSystemState onSensorEvent(SensorEvent sensorEvent) {
        return null;
    }

    @Override
    public AlarmSystemState enterPassword(String password, String secret) {
        return this;
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.OFF;
    }
}

