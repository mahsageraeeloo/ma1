package ir.ma.mahsa.business;

import java.time.Instant;

/**
 * Created by mahsa on 2/24/2019.
 */
public class Car {
    private Integer id;
    private Integer x;
    private Integer y;
    private Integer xDir;
    private Integer yDir;
    //???
    private boolean running = false;
    private Instant lastUpdated = Instant.MAX;
    //???

    public Car() {
    }

    public Car(Integer x, Integer y, Integer xDir, Integer yDir) {
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getxDir() {
        return xDir;
    }

    public void setxDir(Integer xDir) {
        this.xDir = xDir;
    }

    public Integer getyDir() {
        return yDir;
    }

    public void setyDir(Integer yDir) {
        this.yDir = yDir;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!id.equals(car.id)) return false;
        if (x != null ? !x.equals(car.x) : car.x != null) return false;
        if (y != null ? !y.equals(car.y) : car.y != null) return false;
        if (xDir != null ? !xDir.equals(car.xDir) : car.xDir != null) return false;
        return yDir != null ? yDir.equals(car.yDir) : car.yDir == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (xDir != null ? xDir.hashCode() : 0);
        result = 31 * result + (yDir != null ? yDir.hashCode() : 0);
        return result;
    }

    public boolean isRunning() {
        return running;
    }
}