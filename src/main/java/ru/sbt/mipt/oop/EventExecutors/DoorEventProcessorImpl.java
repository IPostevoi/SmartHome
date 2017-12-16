package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.dto.SmartHome;

/**
 * Created by user7 on 14.10.2017.
 */
public class DoorEventProcessorImpl implements Observer {

    private CommandSender commandSender = new CommandSenderImpl();

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        switch (event.getType()) {

            case DOOR_OPEN:
                smartHome.processAction(new OpenDoor(), event);
                break;

            case DOOR_CLOSED:
                smartHome.processAction(new CloseDoor(), event);
                break;

        }
    }
}
