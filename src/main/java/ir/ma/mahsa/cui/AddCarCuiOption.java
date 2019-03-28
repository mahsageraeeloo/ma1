package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.Car;
import ir.ma.mahsa.business.CarManager;
import ir.ma.mahsa.business.exc.AddCarException;

import java.util.Scanner;

public class AddCarCuiOption extends AbstractCuiOption {

    public AddCarCuiOption() {
        super("Add a new car");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        Car newCar = new Car();
        System.out.println("x");
        newCar.setX(scanner.nextInt());
        System.out.println("xDir");
        newCar.setxDir(scanner.nextInt());
        System.out.println("y");
        newCar.setY(scanner.nextInt());
        System.out.println("yDir");
        newCar.setyDir(scanner.nextInt());
        try {
            int newCarID = cm.addCar(newCar);
            System.out.println("Car added successfully. Its id is " + newCarID);
        } catch (AddCarException e) {
            System.err.println("Adding car failed : "+ e.getMessage());
        }
        return true;
    }
}
