package ru.sbt.mipt.oop;

/**
 * Created by user7 on 14.10.2017.
 */
public class CommandSenderImpl implements CommandSender {

    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Sent command");
    }
}
