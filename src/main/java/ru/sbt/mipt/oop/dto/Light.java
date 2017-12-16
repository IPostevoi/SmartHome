package ru.sbt.mipt.oop.dto;

import ru.sbt.mipt.oop.EventExecutors.Action;
import ru.sbt.mipt.oop.SensorEvent;

public class Light implements HomeUnit{
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void processAction(Action action, SensorEvent event) {
        action.execute(this, event);
    }
}
