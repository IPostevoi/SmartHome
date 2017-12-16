package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.dto.SmartHome;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by bakla410 on 16.12.17.
 */
public class HomeUtils {

    @Autowired
    private HomeReader reader;

    @Autowired
    private HomeSaver saver;

    private String properties;

    public SmartHome getHomeFromProp() throws Exception {
        Properties prop = loadProperties(properties);
        String value = prop.getProperty("smart-home.load");
        return reader.read(value);
    }

    public void save(SmartHome smartHome) throws Exception {
        Properties prop = loadProperties(properties);
        String value = prop.getProperty("smart-home.save");

        saver.save(value, smartHome);
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    private Properties loadProperties(String path) throws IOException {

        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(path);
        properties.load(in);
        in.close();

        return properties;
    }
}
