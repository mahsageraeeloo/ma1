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
        try {
            new StateManagerFileImpl().retrieveState(InstanceRegistry.lookupSingle(CarManager.class));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        CarManager cm = InstanceRegistry.lookupSingle(CarManager.class);
        IStateManager iStateManager = InstanceRegistry.lookupSingle(IStateManager.class);
        try {
            iStateManager.saveState(cm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
