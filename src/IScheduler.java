/**
 * Created by mahsa on 3/4/2019.
 */
public interface IScheduler {

    int schedule(Schedulable schedulable, int intervalSec);

    void stop(int id);
}
