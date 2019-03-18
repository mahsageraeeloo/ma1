package ir.ma.mahsa.gui;

import ir.ma.mahsa.business.Car;
import ir.ma.mahsa.business.CarManager;
import ir.ma.mahsa.business.InstanceRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CarListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarManager cm = InstanceRegistry.lookupSingle(CarManager.class);
        PrintWriter writer = response.getWriter();
        writer.write("[");
        boolean isFirstCar = true;
        for (Car car : cm.getCarList()
        ) {
            if (isFirstCar) {
                writer.append("{");
                isFirstCar = false;
            } else {
                writer.append(",{");
            }
            writer.append("\"id\":\"" + car.getId() + "\",");
            writer.append("\"X\":\"" + car.getX() + "\",");
            writer.append("\"Y\":\"" + car.getY() + "\"");
            writer.append("}");
        }
        writer.append("]");
        writer.flush();
    }
}
