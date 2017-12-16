package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.Exceptions.HomeException;
import com.google.gson.Gson;
import ru.sbt.mipt.oop.dto.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by bakla410 on 15.12.17.
 */
public class JsonReader implements HomeReader {

    @Override
    public SmartHome read(String path) throws HomeException {
        Gson gson = new Gson();
        String json;
        try  {

            json = new String(Files.readAllBytes(Paths.get(path)));

        } catch (IOException e) {
            throw new HomeException(e, "Exception occurred while reading config file");
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
