package testHome;

import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarmSystem.AlarmStateEnum;
import ru.sbt.mipt.oop.alarmSystem.AlarmSystem;

import static org.mockito.Mockito.mock;

/**
 * Created by bakla410 on 17.12.17.
 */
public class TestAlarm {

    private SensorEvent event = mock(SensorEvent.class);

    @Test
    public void alarmSystemTurnOnTest() {
        AlarmSystem system = new AlarmSystem("12345");
        system.turnOn();
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
        system.enterPassword("12345");
        system.onSensorEvent(event);
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.ON), true);
        system.turnOff();
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
    }

    @Test
    public void alarmSystemTurnOffTest() {
        AlarmSystem system = new AlarmSystem("12345");
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.OFF), true);
        system.enterPassword("12345");
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.OFF), true);
        system.onSensorEvent(event);
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.OFF), true);
    }

    @Test
    public void alarmSystemTurnOnIncorrectTest() {
        AlarmSystem system = new AlarmSystem("12345");
        system.turnOn();
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
        system.enterPassword("qwerty");
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
    }

    @Test
    public void alarmSystemWaitForPass() {
        AlarmSystem system = new AlarmSystem("12345");
        system.turnOn();
        system.onSensorEvent(event);
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
        system.onSensorEvent(event);
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
    }
}
