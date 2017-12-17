package ru.sbt.mipt.oop.alarmSystem;

import ru.sbt.mipt.oop.SensorEvent;

public class StateWait implements AlarmSystemState {

    private AlarmSystemState previous;

    public StateWait(AlarmSystemState alarmSystemState) {
        previous = alarmSystemState;
    }

    @Override
    public AlarmSystemState turnOn() {
        return this;
    }

    @Override
    public AlarmSystemState turnOff() {
        return previous;
    }

    @Override
    public AlarmSystemState onSensorEvent(SensorEvent sensorEvent) {
        return this;
    }

    @Override
    public AlarmSystemState enterPassword(String password, String secret) {
        if (password.equals(secret)) {
            System.out.println("Correct Password");
            if (previous instanceof StateOn)
                return new StateOff();

            if (previous instanceof StateOff)
                return new StateOn();

            if (previous instanceof StateAlarm)
                return new StateOn();
        }
        else
            System.out.println("Incorrect Password");
        return this;
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.WAIT;
    }
}
