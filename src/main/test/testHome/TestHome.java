package testHome;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sbt.mipt.oop.EventExecutors.*;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.Door;
import ru.sbt.mipt.oop.dto.Light;
import ru.sbt.mipt.oop.dto.Room;
import ru.sbt.mipt.oop.dto.SmartHome;
import ru.sbt.mipt.oop.enums.SensorEventType;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

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
    public void compositeTest() {

        LightOn lightOn = mock(LightOn.class);
        EventListener listener = mock(EventListener.class);
        when(listener.listen()).thenReturn(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
        SensorEvent sensorEvent = listener.listen();

        LightEventProcessorImpl lightEventProcessor = mock(LightEventProcessorImpl.class);
        DoorEventProcessorImpl doorEventProcessor = new DoorEventProcessorImpl();

        EventsObservable observable = new EventsObservable();
        observable.add(lightEventProcessor);
        observable.add(doorEventProcessor);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                smartHome.processAction(lightOn, sensorEvent);
                return null;
            }
        }).when(lightEventProcessor).processEvent(smartHome, sensorEvent);


        observable.notifyObservers(smartHome, sensorEvent);
        verify(lightOn).execute(smartHome, sensorEvent);
        verify(lightOn).execute(smartHome.getRooms().toArray()[0], sensorEvent);

    }

    @Test
    public void eventObserverTest() {

        SensorEvent sensorEvent = mock(SensorEvent.class);
        LightEventProcessorImpl lightEventProcessor = mock(LightEventProcessorImpl.class);
        DoorEventProcessorImpl doorEventProcessor = mock(DoorEventProcessorImpl.class);
        EventsObservable observable = new EventsObservable();
        observable.add(lightEventProcessor);
        observable.add(doorEventProcessor);

        observable.notifyObservers(smartHome, sensorEvent);
        verify(doorEventProcessor).processEvent(smartHome, sensorEvent);
        verify(lightEventProcessor).processEvent(smartHome, sensorEvent);
    }





}
