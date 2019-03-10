import ir.ma.mahsa.business.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Created by mahsa on 2/24/2019.
 */
public class CarManagerTest {

    private MockedScheduler mockedScheduler;

    @Before
    public void setup() {
        mockedScheduler = new MockedScheduler();
    }

    @Test
    public void startMoving() throws Exception {
        CarManager cm = new CarManager();
        Car c = getCar(1, 1, 1, 2);
        cm.addCar(c);
        mockedScheduler.setSecondsPassed(3);
        cm.startMoving();
        Assert.assertTrue(cm.isCarState());
        Assert.assertTrue(c.getX() == 4);
        Assert.assertTrue(c.getY() == 7);
    }

    @Test
    public void stopMoving() throws Exception {
        CarManager cm = new CarManager();
        Car c1 = getCar(1, 1, 1, 2);
        cm.addCar(c1);
        mockedScheduler.setSecondsPassed(2);
        cm.startMoving();
        cm.stopMoving();
        Assert.assertFalse(cm.isCarState());
        Assert.assertFalse(c1.isRunning());
        Car c2 = getCar(2,3,1,1);
        Thread.sleep(1000);
        Assert.assertEquals(2, (int) c1.getX());
        Assert.assertEquals(3, (int) c1.getY());
        Thread.sleep(1000);
        Assert.assertEquals(2, (int) c1.getX());
        Assert.assertEquals(3, (int) c1.getY());
    }

    @org.junit.Test
    public void addCar() throws Exception {
        /*
        There is a problem here, we do not have any control of changing ID of a car, in this way we may have
        different cars with the same ID
         */
        CarManager cm = new CarManager();
        List<Car> temp = cm.getCarList();
        int sizeB4;

        Car c = getCar(1, 1, 1, 1);
        sizeB4 = temp.size();
        cm.addCar(c);
        temp = cm.getCarList();
        Assert.assertEquals(sizeB4 + 1, temp.size());
        Assert.assertTrue(temp.contains(c));

        c = getCar(2, 3, 2, 3);
        sizeB4 = temp.size();
        cm.addCar(c);
        temp = cm.getCarList();
        Assert.assertEquals(sizeB4 + 1, temp.size());
        Assert.assertTrue(temp.contains(c));
    }

    @org.junit.Test
    public void removeCar() throws Exception {
        CarManager cm = new CarManager();
        List<Car> temp;

        Car c = getCar(1, 1, 1, 1);
        cm.addCar(c);

        c = getCar(1, 1, 1, 1);
        cm.addCar(c);

        Car c1 = getCar(2, 3, 1, 5);
        cm.addCar(c1);

        temp = cm.getCarList();
        Assert.assertEquals(temp.size(), 3);

        cm.removeCar(c.getId());
        temp = cm.getCarList();
        Assert.assertFalse(temp.contains(c));
        Assert.assertEquals(temp.size(), 2);

        cm.removeCar(c1.getId());
        temp = cm.getCarList();
        Assert.assertFalse(temp.contains(c1));
    }

    private Car getCar(int x, int y, int xDir, int yDir) {
        Car c = new Car();
        c.setX(x);
        c.setY(y);
        c.setxDir(xDir);
        c.setyDir(yDir);
        return c;
    }

    @org.junit.Test
    public void changeDirection() throws Exception {
        Instant s = Instant.now();
        System.out.println("s:" + s.getEpochSecond());
        Thread.sleep(2700);
        Instant s2 = Instant.now();
        System.out.println("s2:" + s2.getEpochSecond());
        System.out.println("between:" + Duration.between(s, s2).getSeconds());
    }

    class MockedScheduler implements IScheduler {

        private int secondsPassed;

        public MockedScheduler() {
            InstanceRegistry.register(this);
        }

        @Override
        public int schedule(Schedulable schedulable, int intervalSec) {
            int times = secondsPassed / intervalSec;
            for (int i = 0; i < times; i++) {
                schedulable.run();
            }
            return times;
        }

        @Override
        public void stop(int id) {
        }

        public void setSecondsPassed(int secondsPassed) {
            this.secondsPassed = secondsPassed;
        }
    }
}