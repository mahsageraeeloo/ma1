package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class RemoveCarCuiOption extends AbstractCuiOption {

    public RemoveCarCuiOption() {
        super("Remove a car");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        System.out.println("Enter car ID :");
        cm.removeCar(scanner.nextInt());
        System.out.println("Car removed successfully.");
        return true;
    }
}
