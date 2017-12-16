package ru.sbt.mipt.oop.dto;

import ru.sbt.mipt.oop.EventExecutors.Action;
import ru.sbt.mipt.oop.SensorEvent;

import java.util.Collection;

public class Room implements HomeUnit {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void processAction(Action action, SensorEvent event) {
        action.execute(this, event);

        for (Light light: lights) {
            light.processAction(action, event);
        }

        for (Door door: doors) {
            door.processAction(action, event);
        }

    }
}
