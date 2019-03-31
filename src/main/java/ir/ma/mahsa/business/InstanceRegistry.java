package ir.ma.mahsa.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mahsa on 3/4/2019.
 */
public class InstanceRegistry {

    private static InstanceRegistry instance = new InstanceRegistry();

    private InstanceRegistry() {
    }

    public static InstanceRegistry getInstance() {
        return instance;
    }

    private List<Object> registry = new ArrayList<>();

    public void register(Object o) {
        registry.add(o);
    }

    public void deregister(Object o) {
        registry.remove(o);
    }

    public <O> O lookupSingle(Class<O> objClass) {
        return (O) registry.stream().filter(o -> objClass.isInstance(o)).findFirst().get();
    }

    public <O> List<O> lookupMultiple(Class<O> objClass) {
        return (List<O>) registry.stream().filter(o -> objClass.isInstance(o)).collect(Collectors.toList());
    }

    public void initObjects() {
        for (Object o : registry) {
            if (o instanceof IHasLifeCycle) {
                IHasLifeCycle hasLifeCycle = (IHasLifeCycle) o;
                hasLifeCycle.initialize();
            }
        }
    }

    public void destroyObjects() {
        for (Object o : registry) {
            if (o instanceof IHasLifeCycle) {
                IHasLifeCycle hasLifeCycle = (IHasLifeCycle) o;
                hasLifeCycle.destroy();
            }
        }
    }
}
