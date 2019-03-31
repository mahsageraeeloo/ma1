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
import java.io.Writer;

public class CarListServlet extends HttpServlet {
    public CarListServlet() {
        InstanceRegistry.getInstance().register(this);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        getCarListJson(writer);
    }

    public void getCarListJson(Writer writer) throws IOException {
        CarManager cm = InstanceRegistry.getInstance().lookupSingle(CarManager.class);
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
