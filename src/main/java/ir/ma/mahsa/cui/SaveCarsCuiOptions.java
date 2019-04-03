package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;
import ir.ma.mahsa.business.IStateManager;
import ir.ma.mahsa.business.IStatefull;
import ir.ma.mahsa.business.InstanceRegistry;

import java.io.IOException;
import java.util.Scanner;

public class SaveCarsCuiOptions extends AbstractCuiOption {
    public SaveCarsCuiOptions() {
        super("Save");
    }

    @Override
    public boolean runOption(Scanner scanner, CarManager cm) {
        IStateManager iStateManager = InstanceRegistry.getInstance().lookupSingle(IStateManager.class);
        try {
            iStateManager.saveState(cm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}