package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;
import ir.ma.mahsa.business.InstanceRegistry;
import ir.ma.mahsa.business.SchedulerTimerImpl;
import ir.ma.mahsa.business.StateManagerFileImpl;

import java.util.Scanner;

/**
 * Created by mahsa on 2/23/2019.
 */
public class MainClass {
    public static void main(String[] args) { // I think this means that we ignore the exception
        //pause, resume, boundries, interval and max cars as parameters
        int input;
        SchedulerTimerImpl scheduler = new SchedulerTimerImpl();
        CarManager cm = new CarManager();
        new StateManagerFileImpl();
        InstanceRegistry.getInstance().initObjects();
        Scanner scanner = new Scanner(System.in);
        boolean loopFlag = true;

        do {
            System.out.println("Please enter an operation:");
            int optionIndex = 0;
            for (CuiOptionHolder option : CuiOptionHolder.values()) {
                optionIndex++;
                System.out.println(optionIndex + ": " + option.getOption().getUserMessage());
            }
            input = scanner.nextInt();
            AbstractCuiOption cuiOption = CuiOptionHolder.values()[input - 1].getOption();
            loopFlag = cuiOption.runOption(scanner, cm);
        } while (loopFlag);
        InstanceRegistry.getInstance().destroyObjects();
    }
}
