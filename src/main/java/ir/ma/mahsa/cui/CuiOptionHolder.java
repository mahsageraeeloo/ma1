package ir.ma.mahsa.cui;

public enum CuiOptionHolder {
    ADD_CAR     (new AddCarCuiOption()),
    REMOVE_CAR  (new RemoveCarCuiOption()),
    START_MOVING(new StartMovingCuiOption()),
    STOP_MOVING (new StopMovingCuiOption()),
    SHOW_CARS   (new ShowCarsCuiOption()),
    EXIT        (new ExitCuiOption());

    private AbstractCuiOption option;

    CuiOptionHolder(AbstractCuiOption option) {
        this.option = option;
    }

    public AbstractCuiOption getOption() {
        return option;
    }
}
