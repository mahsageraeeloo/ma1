package ir.ma.mahsa.business;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mahsa on 3/4/2019.
 */
public class SchedulerTimerImpl implements IScheduler {

    private Timer timer;
    private Map<Integer, TimerTask> taskMap;
    private int taskId;

    public SchedulerTimerImpl() {
        InstanceRegistry.register(this);
        timer = new Timer(true);
        taskMap = new HashMap<>();
    }

    @Override
    public int schedule(Schedulable schedulable, int intervalSec) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!schedulable.run()) {
                    cancel();
                }
            }
        };
        timer.schedule(task, 0, intervalSec * 1000);
        taskMap.put(++taskId, task);
        return taskId;
    }

    @Override
    //we can stop a task or stop whole tasks of scheduler
    public void stop(int id) {
        taskMap.get(id).cancel();
    }
}
