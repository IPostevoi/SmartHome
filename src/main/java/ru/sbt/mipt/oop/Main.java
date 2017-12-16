package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventExecutors.EventListener;
import ru.sbt.mipt.oop.EventExecutors.EventsObservable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.dto.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

/**
 * Created by bakla410 on 16.12.17.
 */
//@Component
public class Main {

    @Autowired
    private HomeUtils homeUtils;

    @Autowired
    private EventListener eventListener;

    @Autowired
    private EventsObservable observable;

    public void start() throws Exception {

        SmartHome smartHome = homeUtils.getHomeFromProp();

        SensorEvent event = eventListener.listen();

        while (event != null) {
            observable.notifyObservers(smartHome, event);
            homeUtils.save(smartHome);
            event = eventListener.listen();
        }
    }

}
