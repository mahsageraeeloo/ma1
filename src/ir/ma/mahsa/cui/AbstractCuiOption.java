package ir.ma.mahsa.cui;

import ir.ma.mahsa.business.CarManager;

import java.util.Scanner;

public abstract class AbstractCuiOption {

    private final String userMessage;

    public AbstractCuiOption(String userMessage) {
        this.userMessage = userMessage;
    }

    public abstract boolean runOption(Scanner scanner, CarManager cm);

    public String getUserMessage() {
        return userMessage;
    }
}
