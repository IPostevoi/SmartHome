package ru.sbt.mipt.oop.EventExecutors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.dto.Door;

/**
 * Created by bakla410 on 15.12.17.
 */
public class CloseDoor implements Action {

    @Override
    public void execute(Object object, SensorEvent event) {
        if (object instanceof Door) {
            if (event.getObjectId().equals(((Door) object).getId())) {
                ((Door) object).setOpen(false);
            }
        }
    }
}
