package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class ExitCuiOption extends AbstractCuiOption {

    public ExitCuiOption() {
        super("Exit");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        cm.stopMoving();
        return false;
    }
}
