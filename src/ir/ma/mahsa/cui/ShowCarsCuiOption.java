package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.Car;
import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class ShowCarsCuiOption extends AbstractCuiOption {

    public ShowCarsCuiOption() {
        super("Show cars");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        System.out.println("ID  X   Y");
        for (Car car: cm.getCarList()) {
            System.out.printf("%d   %d  %d\n", car.getId(), car.getX(), car.getY());
        }
        return true;
    }
}
