package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public class ResetCuiOptions extends AbstractCuiOption {
    public ResetCuiOptions() {
        super("Reset");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        cm.removeAll();
        return true;
    }
}
