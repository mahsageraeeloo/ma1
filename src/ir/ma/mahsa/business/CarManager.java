package ir.ma.mahsa.business;

import ir.ma.mahsa.business.exc.AddCarException;

import java.util.*;

/**
 * Created by mahsa on 2/24/2019.
 */
public class CarManager {
    private HashMap<Integer, Car> carList = new HashMap<>(10);
    private Integer lastCarId = 0;
    private boolean carState = false;
    private static final Integer MAX_CARS = 1;

    private IScheduler scheduler;

    /*
    I will add an argument for max numbers of cars in car manager
     */
    public CarManager() {
        scheduler = InstanceRegistry.lookupSingle(IScheduler.class);
    }

    public int addCar(Car c) throws AddCarException {
        if (carList.size() >= MAX_CARS) {
            throw new AddCarException("The field is full.");
        }
        if (c.getId() != null && carList.containsValue(c)) {
            throw new AddCarException("This car added before!");
        }
        c.setId(++lastCarId);
        carList.put(c.getId(), c);
        return c.getId();
    }

    public void removeCar(Integer id) {
        carList.remove(id).setRunning(false);
    }

    public void startMoving() {
        this.setCarState(true);
        for (Car car : this.carList.values()) {
            startCarThread(car);
        }
    }

    private void startCarThread(final Car car) {
        car.setRunning(true);
        Schedulable schedulable = () -> {
            car.setX(car.getX() + car.getxDir());
            car.setY(car.getY() + car.getyDir());
            return (isCarState() && car.isRunning());
        };
        runParallel(schedulable);
    }

    private void runParallel(Schedulable schedulable) {
        scheduler.schedule(schedulable, 1);
    }

    public void stopMoving() {
        setCarState(false);
        for (Car car : this.carList.values()) {
            car.setRunning(false);
        }
    }

    public List<Car> getCarList() {
        ArrayList<Car> temp = new ArrayList<>(carList.values());
        Collections.sort(temp, Comparator.comparingInt(Car::getId));
        return temp;
    }

    public boolean isCarState() {
        return carState;
    }

    public void setCarState(boolean carState) {
        this.carState = carState;
    }

}