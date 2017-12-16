package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.SensorEvent;

public class AlarmSystem {
    private AlarmSystemState alarmSystemState;
    private String password;


    public AlarmSystem(String password) {
        alarmSystemState = new StateOff();
        this.password = password;
    }

    public void turnOn() {
        alarmSystemState = alarmSystemState.turnOn();
    }

    public void turnOff() {
        alarmSystemState = alarmSystemState.turnOff();
    }

    public void onSensorEvent(SensorEvent sensorEvent) {
        alarmSystemState = alarmSystemState.onSensorEvent(sensorEvent);
    }

    public void enterPassword(String password) {
        alarmSystemState = alarmSystemState.enterPassword(password, this.password);
    }

    public AlarmStateEnum getState() {
        return alarmSystemState.getState();
    }
}
