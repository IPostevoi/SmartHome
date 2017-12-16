package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.SmartHome;

/**
 * Created by user7 on 14.10.2017.
 */
public class LightEventProcessorImpl implements Observer {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON:
                smartHome.processAction(new LightOn(), event);
                break;

            case LIGHT_OFF:
                smartHome.processAction(new LightOff(), event);
                break;
        }
    }
}
