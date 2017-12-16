package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.Exceptions.HomeException;
import ru.sbt.mipt.oop.dto.SmartHome;

/**
 * Created by bakla410 on 15.12.17.
 */
public interface HomeReader {

    SmartHome read(String path) throws HomeException;

}
