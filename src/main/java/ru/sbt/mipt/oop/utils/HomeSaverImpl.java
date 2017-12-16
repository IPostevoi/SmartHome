package ru.sbt.mipt.oop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.dto.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by bakla410 on 16.12.17.
 */
public class HomeSaverImpl implements HomeSaver {

    @Override
    public void save(String value, SmartHome smartHome) {
        Path path = Paths.get(value);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(gson.toJson(smartHome));
        } catch (IOException e) {}
    }
}
