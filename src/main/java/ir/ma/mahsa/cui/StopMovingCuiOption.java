package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class StopMovingCuiOption extends AbstractCuiOption {

    public StopMovingCuiOption() {
        super("Stop running");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        cm.stopMoving();
        return true;
    }
}
