package ir.ma.mahsa.business;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mahsa on 3/3/2019.
 */
public class SchedulerThreadImpl implements IScheduler {
    private HashMap<Integer, SchedulerTask> tasks = new HashMap<>(10);
    private int lastTaskID = 0;

    public SchedulerThreadImpl() {
        InstanceRegistry.register(this);
    }

    @Override
    public int schedule(Schedulable schedulable, int intervalSec) {
        SchedulerTask s = new SchedulerTask(schedulable, intervalSec);
        tasks.put(lastTaskID, s);
        new Thread(s).start();
        return lastTaskID++;
    }

    @Override
    public void stop(int id) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    private static class SchedulerTask implements Runnable {
        private Schedulable schedulable;
        private int intervalSec;
        private Instant lastExecutionTime = Instant.MAX;
        private Instant periodBegin;
        private Instant startTime = Instant.MAX;
        private Instant stopTime = Instant.MAX;

        public SchedulerTask(Schedulable schedulable, int intervalSec) {
            startTime = Instant.now();
            periodBegin = Instant.now();
            this.schedulable = schedulable;
            this.intervalSec = intervalSec;
        }

        @Override
        public void run() {
            int period;
            Instant instNow;

            do {
                instNow = Instant.now();
                Instant periodEnd = min(stopTime, instNow);
                period = (int) Duration.between(periodBegin, periodEnd).getSeconds();
                periodBegin = instNow;

                for (int i = 0; i <= (period / intervalSec); i++) {
                    schedulable.run();
                }
                if (!stopTime.equals(Instant.MAX)) {
                    return;
                }
                try {
                    Thread.sleep((intervalSec - (period % intervalSec)) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }

        private Instant min(Instant a, Instant b) {
            return a.compareTo(b) < 0 ? a : b;
        }

        public void stop() {
            stopTime = Instant.now();
        }
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (counter.get() < 30) {

                    }
                    Duration nano = Duration.between(start, Instant.now());
                    System.out.println(String.format("%s seconds and %s nano", String.valueOf(nano.getSeconds()), String.valueOf(nano.getNano())));
                    System.exit(0);
                }
            }.start();
        }
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                int value = counter.incrementAndGet();
                Instant now = Instant.now();
                System.out.println(now.getEpochSecond() + " " + now.getNano() + " value = " + value);
            }
        }, 0, 1000);

    }
}
