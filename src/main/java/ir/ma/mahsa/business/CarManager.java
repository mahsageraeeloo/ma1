package ir.ma.mahsa.business;

import ir.ma.mahsa.business.exc.AddCarException;

import java.io.*;
import java.util.*;

/**
 * Created by mahsa on 2/24/2019.
 */
public class CarManager implements Serializable, IStatefull<List<Car>> {
    private HashMap<Integer, Car> carList = new HashMap<>(10);
    private Integer lastCarId = 0;
    private boolean carState = false;
    private static final Integer MAX_CARS = 20;
    private transient IScheduler scheduler;

    /*
    I will add an argument for max numbers of cars in car manager
     */
    public CarManager() {
        scheduler = InstanceRegistry.getInstance().lookupSingle(IScheduler.class);
        InstanceRegistry.getInstance().register(this);
    }

    public void setLastCarId(Integer lastCarId) {
        this.lastCarId = lastCarId;
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
        if (isCarState()) { //?semaphore
            startCarThread(c);
        }
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
            car.setX((car.getX() + car.getxDir()) % 600);
            car.setY((car.getY() + car.getyDir()) % 600);
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

    public void setCarList(HashMap<Integer, Car> carList) {
        this.carList = carList;
    }

    public boolean isCarState() {
        return carState;
    }

    public void setCarState(boolean carState) {
        this.carState = carState;
    }

    public void removeAll() {
        for (Car car : this.getCarList()) {
            this.removeCar(car.getId());
        }
    }

    @Override
    public List<Car> getState() {
        return this.getCarList();
    }

    @Override
    public void setState(List<Car> list) {
        int max = 0;
        HashMap<Integer, Car> cars = new HashMap<>();
        for (Car c : list) {
            int id = c.getId();
            if (id > max) {
                max = c.getId();
            }
            cars.put(id, c);
        }
        this.setLastCarId(max);
        this.setCarList(cars);
    }
}