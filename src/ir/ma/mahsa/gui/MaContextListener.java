package ir.ma.mahsa.gui;

import ir.ma.mahsa.business.CarManager;
import ir.ma.mahsa.business.SchedulerTimerImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MaContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new SchedulerTimerImpl();
        new CarManager();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
