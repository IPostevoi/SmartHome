package ru.sbt.mipt.oop.dto;

import ru.sbt.mipt.oop.EventExecutors.Action;
import ru.sbt.mipt.oop.SensorEvent;

public class Door implements HomeUnit {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void processAction(Action action, SensorEvent event) {
        action.execute(this, event);
    }
}
