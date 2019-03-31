package ir.ma.mahsa.gui;

import ir.ma.mahsa.business.*;
import ir.ma.mahsa.business.exc.AddCarException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarManager cm = InstanceRegistry.getInstance().lookupSingle(CarManager.class);
        IStateManager iStateManager = InstanceRegistry.getInstance().lookupSingle(IStateManager.class);
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("addCar")) {
            try {
                cm.addCar(new Car(Integer.valueOf(request.getParameter("X")), Integer.valueOf(request.getParameter("Y")), Integer.valueOf(request.getParameter("XDir")), Integer.valueOf(request.getParameter("YDir"))));
            } catch (AddCarException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("removeCar")) {
            cm.removeCar(Integer.valueOf(request.getParameter("CarID")));
        } else if (action.equalsIgnoreCase("start")) {
            cm.startMoving();
        } else if (action.equalsIgnoreCase("stop")) {
            cm.stopMoving();
//            cm.persistCarList();
        } else if (action.equalsIgnoreCase("save")) {
//            cm.persistCarList();
            iStateManager.saveState(cm);
        } else if (action.equalsIgnoreCase("reset")) {
            cm.removeAll();
        }
        response.sendRedirect("carManagerUI.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
