package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class StartMovingCuiOption extends AbstractCuiOption {

    public StartMovingCuiOption() {
        super("Start running");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        cm.startMoving();
        return true;
    }
}
