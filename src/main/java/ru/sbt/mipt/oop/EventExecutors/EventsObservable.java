package ru.sbt.mipt.oop.EventExecutors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.SmartHome;

import java.util.List;

/**
 * Created by user7 on 28.10.2017.
 */
public class EventsObservable implements Observable {

    private List<Observer> eventContainer;

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

    public List<Observer> getEventContainer() {
        return eventContainer;
    }

    public void setEventContainer(List<Observer> eventContainer) {
        this.eventContainer = eventContainer;
    }
}
