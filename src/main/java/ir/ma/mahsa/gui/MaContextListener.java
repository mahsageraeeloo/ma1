package ir.ma.mahsa.gui;

import ir.ma.mahsa.business.*;
import ir.ma.mahsa.business.exc.AddCarException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class MaContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new SchedulerTimerImpl();
        new CarManager();
        new StateManagerFileImpl();
        InstanceRegistry.getInstance().initObjects();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        InstanceRegistry.getInstance().destroyObjects();
    }
}
