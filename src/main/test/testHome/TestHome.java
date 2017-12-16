package testHome;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sbt.mipt.oop.alarmSystem.AlarmStateEnum;
import ru.sbt.mipt.oop.alarmSystem.AlarmSystem;
import ru.sbt.mipt.oop.EventExecutors.EventListener;
import ru.sbt.mipt.oop.EventExecutors.Observable;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.Door;
import ru.sbt.mipt.oop.dto.Light;
import ru.sbt.mipt.oop.dto.Room;
import ru.sbt.mipt.oop.dto.SmartHome;
import ru.sbt.mipt.oop.enums.SensorEventType;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by bakla410 on 16.12.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config.xml"})
public class TestHome {

    @Autowired
    private ApplicationContext context;

    private SmartHome smartHome;

    @Before
    public void buildHome() {
        smartHome = new SmartHome();
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", false)), new ArrayList<Door>(), null));
    }

    @Test
    public void testContext() {
        Assert.assertEquals(true, context.getBean("main") != null );
        Assert.assertEquals(true, context.getBean("logger") != null );
        Assert.assertEquals(true, context.getBean("eventListener") != null );
        Assert.assertEquals(true, context.getBean("observable") != null );
        Assert.assertEquals(true, context.getBean("homeUtils") != null );
    }

    @Test
    public void readWriteTest() {
        Exception ex = null;
        try {
            SmartHome smartHome = ((HomeUtils) context.getBean("homeUtils")).getHomeFromProp();
            ((HomeUtils) context.getBean("homeUtils")).save(smartHome);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertEquals(true, ex == null);
    }

    @Test
    public void integrationTest() {

        EventListener listener = mock(EventListener.class);

        when(listener.listen()).thenReturn(new SensorEvent(SensorEventType.LIGHT_ON, "1"));

        ((Observable) context.getBean("observable")).notifyObservers(smartHome, listener.listen());

        Light light =((Light) ((Room) smartHome.getRooms().toArray()[0]).getLights().toArray()[0]);

        Assert.assertEquals(true, light.isOn());

    }

    @Test
    public void alarmSystem() {
        AlarmSystem system = new AlarmSystem("12345");
        system.turnOn();
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
        system.enterPassword("12345");
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.ON), true);
        system.turnOff();
        Assert.assertEquals(system.getState().equals(AlarmStateEnum.WAIT), true);
    }


}
