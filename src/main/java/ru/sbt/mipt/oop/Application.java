package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {


    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(args[0])));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        // начинаем цикл обработки событий
        EventListener eventListener = new EventListenerImpl();

        SensorEvent event = eventListener.getNextSensorEvent();

        EventsObservable observable = new EventsObservable();
        observable.add(new DoorEventProcessorImpl());
        observable.add(new LightEventProcessorImpl());

        while (event != null) {
            observable.notifyObservers(smartHome, event);
            event = eventListener.getNextSensorEvent();
        }
    }
}
