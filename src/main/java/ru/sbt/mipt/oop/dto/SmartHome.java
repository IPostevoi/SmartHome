package ru.sbt.mipt.oop.dto;

import ru.sbt.mipt.oop.EventExecutors.Action;
import ru.sbt.mipt.oop.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeUnit {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void processAction(Action action, SensorEvent event) {
        action.execute(this, event);

        for (Room room: rooms) {
            room.processAction(action, event);
        }
    }
}
