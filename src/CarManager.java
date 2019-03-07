import java.time.Instant;
import java.util.*;

/**
 * Created by mahsa on 2/24/2019.
 */
public class CarManager {
    private HashMap<Integer, Car> carList = new HashMap<>(10);
    private Integer lastCarId = 0;
    private boolean carState = false;
    private Instant startTime = Instant.MAX;
    private Instant stopTime = Instant.MAX;
    private static final Integer maxCars = 10;

    private IScheduler scheduler;

    public CarManager() {
        scheduler = InstanceRegistry.lookupSingle(IScheduler.class);
    }

    public Integer getLastCarId() {
        return lastCarId;
    }

    public void setLastCarId(Integer lastCarId) {
        this.lastCarId = lastCarId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getStopTime() {
        return stopTime;
    }

    public void setStopTime(Instant stopTime) {
        this.stopTime = stopTime;
    }

    public static Integer getMaxCars() {
        return maxCars;
    }

    public int addCar(Car c) {
        if (carList.size() >= maxCars) {
            System.out.println("The field is full. Adding car is failed");
        }
        if (c.getId() != null && carList.containsValue(c)) {
            throw new RuntimeException("");
        }
        c.setId(++lastCarId);
        carList.put(c.getId(), c);
        return c.getId();
    }

    public void removeCar(Integer id) {
        carList.remove(id);
    }

    public void startMoving() throws InterruptedException {
        this.setCarState(true);
        this.startTime = Instant.now();
        for (Car car : this.carList.values()) {
            startCarThread(car);
        }
    }

    private void startCarThread(final Car car) {
        car.setRunning(true);
        car.setLastUpdated(this.startTime);

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
        setStopTime(Instant.now());
        setCarState(false);
    }

    public int changeDirection() {
        throw new RuntimeException("Not implemented!");
    }

    public void setCarList(HashMap<Integer, Car> carList) {
        this.carList = carList;
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