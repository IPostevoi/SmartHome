package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user7 on 28.10.2017.
 */
public class EventsObservable implements Observable {

    private List<Observer> eventContainer = new ArrayList<>();

    @Override
    public void add(Observer processor) {
        eventContainer.add(processor);
    }

    @Override
    public void remove(Observer processor) {
        eventContainer.remove(processor);
    }

    @Override
    public void notifyObservers(SmartHome smartHome, SensorEvent event) {
        for (Observer observer: eventContainer)
            observer.processEvent(smartHome, event);
    }

}
